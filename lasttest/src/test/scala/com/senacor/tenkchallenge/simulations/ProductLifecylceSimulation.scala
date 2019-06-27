package com.senacor.tenkchallenge.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder

import scala.util.Random

class ProductLifecylceSimulation extends Simulation {

  before {
    println("***** Simulation ProductLifecylceSimulation is about to begin! *****")
  }

  after {
    println("***** Simulation ProductLifecylceSimulation has ended! ******")
  }

  val theHttpProtocolBuilder = http
    .baseUrl("http://localhost:8080")

  val feeder = Iterator.continually(Map("name" -> Random.alphanumeric.take(20).mkString))

  val theScenarioBuilder = scenario("Produkt Lifecycle")
    .feed(feeder)
    .exec(
      http("POST /products")
        .post("/products")
        .body(StringBody("{\"name\": ${name}"))
        .check(
          jsonPath("$..id").findAll.saveAs("productId")
        )
    ).doIf("${productId.exists()}") {
      exec(
        http("DELETE /products")
          .delete("/products")
          .queryParam("productId", "${productId}")
      )
  }

  setUp(
    theScenarioBuilder.inject(atOnceUsers(1))
  ).protocols(theHttpProtocolBuilder)
}