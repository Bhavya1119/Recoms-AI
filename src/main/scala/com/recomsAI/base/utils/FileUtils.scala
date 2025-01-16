package com.recomsAI.base.utils

import com.fasterxml.jackson.databind.{ObjectMapper, PropertyNamingStrategy}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.recomsAI.exception.NotFoundException
import org.slf4j.LoggerFactory

import java.io.{File, FileNotFoundException, IOException, InputStream}
import java.util.Properties

/**
 * helper object to fetch contents of any file
 * Using it to fetch contents of application.properties
 */
object FileUtils {

  private val logger = LoggerFactory.getLogger(FileUtils.getClass)

  private val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
  mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)

  def  loadPropertiesFromClassPath(fileName : String): Properties = {

    logger.debug("File Name : {} ", fileName)
    var props : Properties = null
    var inputStream : InputStream = null

    try{
      props = new Properties()
      inputStream = Thread.currentThread().getContextClassLoader.getResourceAsStream(fileName)
      logger.debug("Input Stream : {} ", inputStream)
      props.load(inputStream)
      props
    }catch {
      case e1 : FileNotFoundException => logger.info("file not found {} ",fileName)
        throw e1
      case e2 : IOException => logger.info("I/O exception , check inputstream ...... ")
        throw e2
    }

  }

}
