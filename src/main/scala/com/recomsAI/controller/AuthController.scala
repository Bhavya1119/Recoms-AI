package com.recomsAI.controller

import com.recomsAI.Driver.sessionToken
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

    val userAttributes: java.util.Map[String, Object] = authToken.getPrincipal().getAttributes()

    logger.info(s"Welcome ${userAttributes.get("display_name")}!")

    val baseController = new BaseController()

    baseController.home()
  }
}
