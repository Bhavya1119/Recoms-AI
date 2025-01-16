package com.recomsAI.controller

import com.recomsAI.base.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthController {

  private val logger = LoggerFactory.getLogger(classOf[AuthController])
  private var authUserID : String = _

  @GetMapping(Array("/home"))
  def AuthCentre(authToken: OAuth2AuthenticationToken): String = {
    if (authToken == null) {
      logger.info("OAuth2AuthenticationToken is null")
      return "Authentication failed"
    }
    val userAttributes: java.util.Map[String, Object] = authToken.getPrincipal().getAttributes()
    logger.info("UserAttributes : {} " , userAttributes)
    logger.info(s"Welcome ${userAttributes.get("display_name")}!")
    val baseController = new BaseController()
    authUserID = new AuthService().getAuthorizedUserID(userAttributes)
    logger.info("User ID fetched : {}",authUserID)
    baseController.home()
  }
}
