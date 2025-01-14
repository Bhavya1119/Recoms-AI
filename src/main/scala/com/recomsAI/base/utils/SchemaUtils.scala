package com.recomsAI.base.utils

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.recomsAI.base.enitity.album.{Album,Artist}
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

import scala.collection.JavaConverters.seqAsJavaListConverter

@Service
class SchemaUtils {

  private val logger = LoggerFactory.getLogger(classOf[SchemaUtils])


  private[base] def getAlbumDetailsFromResponse(response: String): java.util.List[Album] = {

    logger.debug("####### Fetching Album Details from response ############")

    val objectMapper = new ObjectMapper()
    val rootNode = objectMapper.readTree(response)
    try{
      val items = rootNode.get("items")
      extractAlbumDetails(items)
    }catch {
      case e : Exception => logger.info("Exception while fetching album Details , ensure response is ok ..... ")
        throw e
    }

  }

  /**
   * Method that extract useful info from api response
   * @param rootNode takes in a rootNode of items from parent method
   * @return list of album object containing Artist and track info
   */

  private def extractAlbumDetails(rootNode : JsonNode) : java.util.List[Album] = {
    val albums = scala.collection.mutable.HashSet[Album]()
      rootNode.forEach(itemArray => {
        val itemArtist = itemArray.get("artists").get(0)
        val artist = new Artist(
          itemArtist.get("name").asText(),
          itemArtist.get("id").asText(),
          itemArray.get("external_urls").get("spotify").asText()
        )
        val artistList = new java.util.LinkedList[Artist]
        artistList.add(artist)

        val track = new Album(
          itemArray.get("id").asText(),
          itemArray.get("name").asText(),
          itemArray.get("duration_ms").asLong(),
          artistList
        )
        albums += track
      })
  logger.info("##################### Details fetched successfully ####################")
    albums.toList.asJava
  }







}
