package com.recomsAI.controller

import com.recomsAI.spark.SparkService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BaseController {

  private val logger = LoggerFactory.getLogger(classOf[BaseController])

  @GetMapping(Array("/"))
  def home() : String = {
    "index.html"
  }
  @GetMapping(Array("/sparkJob"))
  def runSparkJob()  = {
    SparkService.runSparkJob()
    "success"
  }
}
