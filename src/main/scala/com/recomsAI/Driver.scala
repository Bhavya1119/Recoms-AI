package com.recomsAI

import org.slf4j.{Logger, LoggerFactory}
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.{CorsRegistry, WebMvcConfigurer, WebMvcConfigurerAdapter}


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

  def main (args : Array[String]) : Unit = {
    logger.info("############# Starting Application Context ##################")
    SpringApplication.run(classOf[Driver])
  }
}
