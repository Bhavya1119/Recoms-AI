package com.recomsAI

import com.recomsAI.base.utils.FileUtils
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.core.OAuth2AccessToken
import org.springframework.web.servlet.config.annotation.{CorsRegistry, WebMvcConfigurer, WebMvcConfigurerAdapter}

import java.util.Properties


@SpringBootApplication
class Driver {

  @Bean
  def corsConfigurer() : WebMvcConfigurer = {
    new WebMvcConfigurerAdapter (){
      override def addCorsMappings(registry: CorsRegistry): Unit = {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
      }
    }
  }


}

object Driver {
  private val logger: Logger = LoggerFactory.getLogger(Driver.getClass)
  var props : Properties = _
  var sessionToken : OAuth2AccessToken = _

  def init() : Properties = {
    logger.debug("################# Initializing Properties ########################")
    val props = FileUtils.loadPropertiesFromClassPath("application.properties")
    logger.debug("################# Properties Initialized ######################")
    props
  }

  def main (args : Array[String]) : Unit = {
    logger.info("############# Starting Application Context ##################")
    SpringApplication.run(classOf[Driver])
  }
}
