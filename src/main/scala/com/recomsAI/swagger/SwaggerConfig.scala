package com.recomsAI.swagger

import com.recomsAI.Driver
import com.recomsAI.base.constants.Constants
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.builders.{PathSelectors, RequestHandlerSelectors}
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Configurer for base package com.recomsAI , allowing scala support
 */
@EnableSwagger2
@EnableWebMvc
class SwaggerConfig {

  @Bean
  def api() : Docket = {
    val props = Driver.init()
    new Docket(DocumentationType.SWAGGER_2)
      .select
      .apis(RequestHandlerSelectors.basePackage(props.getProperty(Constants.BASE_PACKAGE)))
      .paths(PathSelectors.any)
      .build
  }

}
