package com.recomsAI.controller

import com.recomsAI.Driver.{authWorkspace, sessionToken}
import com.recomsAI.workspace.Workspace
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.client.{OAuth2AuthorizedClient, OAuth2AuthorizedClientService}
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthController {

  private val logger = LoggerFactory.getLogger(classOf[AuthController])

  @Autowired
  private var authorizedClientService: OAuth2AuthorizedClientService = _


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

  @GetMapping(Array("/home"))
  def AuthCentre(authToken: OAuth2AuthenticationToken): String = {

    if (authToken == null) {
      logger.info("OAuth2AuthenticationToken is null")
      return "Authentication failed"
    }
    val client : OAuth2AuthorizedClient = authorizedClientService
                                          .loadAuthorizedClient(
                                            authToken.getAuthorizedClientRegistrationId,
                                            authToken.getName)

    sessionToken = client.getAccessToken

    val userAttributes: java.util.Map[String, Object] = authToken.getPrincipal.getAttributes
    logger.info("user attributes : {} ", userAttributes )
    logger.info(s"Welcome ${userAttributes.get("display_name")}!")

    authWorkspace = generateWorkspace(userAttributes)

    val baseController = new BaseController()
    baseController.home()
  }
}
