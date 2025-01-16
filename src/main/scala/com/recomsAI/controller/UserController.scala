package com.recomsAI.controller

import com.recomsAI.Driver
import com.recomsAI.Driver.props
import com.recomsAI.base.service.User.UserService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class UserController {

  private val logger = LoggerFactory.getLogger(classOf[UserController])

  @GetMapping(Array("/topArtists"))
  private def fetchTopNArtists()  = {
    logger.info("Fetching top artists ..... ")
    try{
      props = Driver.init()
    }catch {
      case e : Exception => logger.info("Exception occurred .. ")
        throw e
    }
    new UserService().getTopArtists(props)
  }

}
