package com.recomsAI.controller


import com.recomsAI.Driver.{authWorkspace, sessionToken}
import com.recomsAI.workspace.Workspace
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.{OAuth2AuthorizedClient, OAuth2AuthorizedClientService}
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, ResponseBody}



@Controller
@RequestMapping(Array("/api/v1"))
class AuthController {

  private val logger = LoggerFactory.getLogger(classOf[AuthController])

  @Autowired
  private var authorizedClientService: OAuth2AuthorizedClientService = _

  /**
   * Helper method to generate a user workspace
   * users workspace maintains the details of the user from the authentication
   * token
   * @param userAttribute
   * @return
   */
  private def generateWorkspace (userAttribute : java.util.Map[String,Object]): Workspace = {
    logger.debug("####################### Initializing workspace ############################ ")
    try{

      val workspaceName = s"workspace_${userAttribute.get("id")}_${userAttribute.get("country")}"
      val workspaceId = s"workspace_${userAttribute.get("id")}_${userAttribute.get("country")}"
      val workspaceEmail = s"workspace_${userAttribute.get("email")}_${userAttribute.get("country")}"
      val workspaceTimestamp = System.currentTimeMillis()
      val workspace = new Workspace()
      workspace.setWorkspace(workspaceName = Some(workspaceName),
        workspaceId = Some(workspaceId),
        workspaceTimeStamp = Some(workspaceTimestamp),
        workspaceEmail = Some(workspaceEmail))
      workspace.setUser(userAttribute.get("id").toString,userAttribute.get("display_name").toString)

      workspace

    }catch {
      case e : Exception => logger.info(s"Exception while initializing workspace for userAttribute ${userAttribute}")
        throw e
    }


  }

  /**
   * Authorization API for authentication from spotify oAuth2
   * @param authToken fetches this authToken from response
   * gets the session token from it
   * generates a user workspace active till user session
   * @return Redirects to home page
   */

  @GetMapping(Array("/signin"))
  def AuthCentre(authToken: OAuth2AuthenticationToken): String = {
  logger.info(s"Auth Token : {}", authToken)


    if (authToken == null) {
      logger.info("OAuth2AuthenticationToken is null")
      "Authentication Error"
    }

    val clientRegistrationId = authToken.getAuthorizedClientRegistrationId
    val principalName = authToken.getName


    val client : OAuth2AuthorizedClient = authorizedClientService.loadAuthorizedClient(clientRegistrationId,principalName)

    sessionToken = client.getAccessToken

    val userAttributes: java.util.Map[String, Object] = authToken.getPrincipal.getAttributes
    logger.info("user attributes : {} ", userAttributes )
    logger.info(s"Welcome ${userAttributes.get("display_name")}!")

    authWorkspace = generateWorkspace(userAttributes)

    val baseController = new BaseController()
    "redirect:/"
  }

  /**
   * Signout API for logging out from the session
   * destroys the workspace and session token
   * @return
   */
  @GetMapping(Array("/signout"))
  def logout(): String = {
    if(sessionToken != null)  sessionToken = null
    if(authWorkspace != null) authWorkspace.clear()
    logger.info("Cleared session and workspace .... ")
    SecurityContextHolder.clearContext()
    "redirect:/"
  }

  /**
   * Auth status check API
   * @param authentication gets input parameter of authentication
   * checks if the authentication is still valid
   * maintains a hashmap of log in state for every time an action
   * is performed on the webpage
   * @return Map of Auth State
   */
  @GetMapping(Array("/auth/status"))
  @ResponseBody
  def checkAuthStatus(authentication: Authentication): java.util.Map[String, Object] = {
    val response = new java.util.HashMap[String, Object]()
    response.put("loggedIn", Boolean.box(authentication != null && authentication.isAuthenticated))
    logger.info("Auth status : {} ", response)
    response
  }

}
