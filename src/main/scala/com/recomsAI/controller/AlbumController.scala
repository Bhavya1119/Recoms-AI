package com.recomsAI.controller

import com.recomsAI.Driver
import com.recomsAI.Driver.props
import com.recomsAI.base.enitity.album.Album
import com.recomsAI.base.service.Album.AlbumService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RestController}

import java.util.Properties

@RestController
class AlbumController {
private val logger = LoggerFactory.getLogger(classOf[AlbumController])


  /**
   * API to fetch Album details for an albumId
   * @param albumId takes in album id as api parameter
   * @return list of album objects containing album info
   */
  @GetMapping(value = Array("/getAlbumDetails/{albumId}"))
  private def getAlbumDetails(@PathVariable albumId : String):java.util.List[Album]  = {
    logger.info("######### Fetching album Details ####################")
    try{
      props = Driver.init()
      val albumDetails = new AlbumService().getAlbumDetails(albumId,props)
      albumDetails
    }
    catch{
      case e : Exception => logger.info("Exception while fetching album details from API .... ")
        throw e
    }


  }

}
