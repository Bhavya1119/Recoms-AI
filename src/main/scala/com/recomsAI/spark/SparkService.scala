package com.recomsAI.spark

import org.apache.spark.sql.SparkSession

object SparkService {

  def runSparkJob() = {
    val spark = SparkSession.builder().appName("test").master("local[*]").getOrCreate()
    val inputPath = "/Users/bhavya.joshi/Desktop/officeWork/codes/SparkPractice/src/main/resources/GymCompetition.csv"
    val df = spark.read.option("header",true).csv(inputPath)
    df.show(false)
    spark.stop()
  }

}
