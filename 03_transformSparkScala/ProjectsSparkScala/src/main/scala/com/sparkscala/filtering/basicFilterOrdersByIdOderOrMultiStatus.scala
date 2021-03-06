package com.sparkscala.filtering

import org.apache.spark.{SparkConf, SparkContext}


object basicFilterOrdersByIdOderOrMultiStatus {
  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName(s"${this.getClass.getName} with spark and scala")
    val sc = new SparkContext(conf)


    val ordersRDD = sc.
      textFile("/user/joseluisillana1709/pruebas_spark/raw/sqoop_import/orders_jl")

    val ordersFilteredRDD = ordersRDD.filter(x =>
      x.split(",")(0).toInt > 1000 &&
        x.split(",")(3).toLowerCase.contains("PENDING".toLowerCase) ||
        x.split(",")(3).toLowerCase.contains("CANCELLED".toLowerCase))


    ordersFilteredRDD.take(5).foreach(el =>
      println(s"Basic filter or complete orders: ${el}"))
  }
}
