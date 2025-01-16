package com.recomsAI.base.service.User

import com.recomsAI.Driver.sessionToken
import com.recomsAI.base.constants.Constants
import com.recomsAI.base.utils.SchemaUtils
import com.recomsAI.exception.{NotFoundException, RateLimitExceededException, UnauthorizedException}
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import scalaj.http.{Http, HttpResponse}

import java.util.Properties

@Service
class UserService {
private val logger = LoggerFactory.getLogger(classOf[UserService])


  def getTopArtists(props : Properties) = {
    val response = topNArtists(props)
    val code = response.code
    try{

      code match {
        case 200 =>
          logger.info("###################### API Success #####################")
          val schemaUtils = new SchemaUtils()
          logger.info("|response| :{}",response)

        case 401 =>
          logger.info("Bad or expired Token . Please re-authenticate token ..... ")
          throw new UnauthorizedException("Authentication failed : Invalid or expired Token ")

        case 403 =>
          logger.info("Bad oAuth request . wrong consumer key, bad nonce, expired timestamp... ")
          throw new NotFoundException("Bad OAuth Request ")
        case 429 =>
          logger.info("Rate limit reached .... ")
          throw new RateLimitExceededException("Rate Limit Exceeded ")
      }


    }catch {
      case e : Exception => logger.info("Exception while getting top artists .... ")
    }


  }

  private def topNArtists(props: Properties): HttpResponse[String] = {

    val authToken = sessionToken.getTokenValue
    val tokenType = sessionToken.getTokenType.getValue

    try {
      val response = Http(props.getProperty(Constants.TOP_ARTISTS))
        .timeout(connTimeoutMs = 600000, readTimeoutMs = 600000)
        .header("Authorization", s"$tokenType $authToken")
        .asString

      response

    } catch {
      case e: Exception => logger.info("Exception while executing API for fetching top artists .....")
        throw e
    }
  }

}
