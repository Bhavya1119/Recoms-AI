package com.recomsAI.controller


import com.recomsAI.Driver
import com.recomsAI.Driver.{authWorkspace, props}
import com.recomsAI.base.enitity.album.Album
import com.recomsAI.base.enitity.user.TopArtists
import com.recomsAI.base.service.Album.AlbumService
import com.recomsAI.base.service.User.UserService
import com.recomsAI.spark.SparkService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, ResponseBody}

@Controller
class BaseController {

  private val logger = LoggerFactory.getLogger(classOf[BaseController])

  /**
   * Home page Redirection
   * @return
   */
  @GetMapping(Array("/"))
  def home() : String = {
    "dashboard.html"
  }

  @GetMapping(Array("/sparkJob"))
  def runSparkJob():String  = {
    SparkService.runSparkJob()
    "success"
  }

  /**
   * API to fetch Album details for an albumId
   * @param albumId takes in album id as api parameter
   * @return list of album objects containing album info
   */
  @GetMapping(value = Array("/getAlbumDetails/{albumId}"))
  @ResponseBody
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

  /**
   * API to fetch top artists for any user
   */
  @GetMapping(Array("/topArtists"))
  @ResponseBody
  private def fetchTopArtists(): java.util.List[TopArtists]  = {
    logger.info("Fetching top artists ..... ")
    try{
      props = Driver.init()
    }catch {
      case e : Exception => logger.info("Exception occurred .. ")
        throw e
    }
    val top = new UserService().getTopArtists(props,authWorkspace)
    top
  }

}

