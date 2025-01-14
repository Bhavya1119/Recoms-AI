package com.recomsAI.base.service


import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AuthService {
private val logger = LoggerFactory.getLogger(classOf[AuthService])

  def getAuthorizedUserID(userAttributes : java.util.Map[String,Object]):String = {
    if(userAttributes.size() == 0 || userAttributes.isEmpty) ""
    logger.info("fetching user ID from authorized response map ....... ")
    try{
      userAttributes.get("id").toString
    }catch {
      case e : Exception => logger.info("Exception while fetching authorized user from response map.... ")
        throw e
    }

  }

}
