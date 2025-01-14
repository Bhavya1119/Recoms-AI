package com.recomsAI.controller

import com.recomsAI.spark.SparkService
import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BaseController {
  private val logger = LoggerFactory.getLogger(classOf[BaseController])
  @GetMapping(Array("/"))
  def home() : String = {
    "index.html"
  }
  @GetMapping(Array("/sparkJob"))
  def runSparkJob()  = {
    SparkService.runSparkJob()
    "success"
  }
  @GetMapping(Array("/home"))
  def home(authToken: OAuth2AuthenticationToken): String = {
    if (authToken == null) {
      logger.info("OAuth2AuthenticationToken is null")
      "Authentication failed"
    }
    val userAttributes: java.util.Map[String, Object] = authToken.getPrincipal().getAttributes()
    logger.info(s"Welcome ${userAttributes.get("display_name")}!")
    home()
  }
}
