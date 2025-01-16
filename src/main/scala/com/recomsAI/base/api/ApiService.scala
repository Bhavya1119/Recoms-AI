package com.recomsAI.base.api

import com.recomsAI.base.constants.Constants
import com.recomsAI.base.utils.FileUtils
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.stereotype.Service
import scalaj.http.Http

import java.util.Properties


@Service
class ApiService {

  private val logger: Logger = LoggerFactory.getLogger(classOf[ApiService])

   def getAuthToken(props : Properties) : String = {

     val config = FileUtils.loadCredentials()
     logger.debug("Credentials : {}", config)

     val clientId       = config.clientID
     val clientSecret   = config.clientSecret
     val grantType      = config.credentialType
     val tokenEndpoint  = props.getProperty(Constants.API_ENDPOINT)
     val contentType    = props.getProperty(Constants.CONTENT_TYPE)

    val formData = Seq(
      "grant_type"      ->      s"$grantType",
      "client_id"       ->      s"$clientId",
      "client_secret"   ->      s"$clientSecret"
    )
    try{
      val response = Http(tokenEndpoint)
                        .timeout(connTimeoutMs = 600000, readTimeoutMs = 600000)
                        .header(Constants.CONTENT_TYPE,contentType)
                        .postForm(formData)
                        .asString

      response.body

    }catch {
      case e: Exception => logger.info("Exception while getting Auth token from Spotify Api Service server ..... ")
        throw e
    }
  }


}

object ApiService{

  private val logger : Logger = LoggerFactory.getLogger(ApiService.getClass)

  def init() : Properties = {
    logger.debug("################# Initializing Properties ########################")
    val props = FileUtils.loadPropertiesFromClassPath("application.properties")
    logger.debug("################# Properties Initialized ######################")
    props
  }
}
