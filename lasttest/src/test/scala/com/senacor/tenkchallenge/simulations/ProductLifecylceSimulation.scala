package com.senacor.tenkchallenge.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder

class ProductLifecylceSimulation extends Simulation {

  before {
    println("***** Simulation ProductLifecylceSimulation is about to begin! *****")
  }

  after {
    println("***** Simulation ProductLifecylceSimulation has ended! ******")
  }

  val theHttpProtocolBuilder = http
    .baseUrl("http://localhost:8080")

  val theScenarioBuilder = scenario("Produkt Lifecycle")
    .exec(
      http("addProduct")
        .post("/products")
        .header("Content-Type", "application/json")
        .formParamMap(Map("id" -> 1, "name" -> "my-product-name"))
    )
    .exec(
      http("getProductById")
        .get("/products")
        .queryParam("productId", 1)
    )
    .exec(
      http("updateProductById")
        .put("/products")
        .queryParam("productId", 1)
        .header("Content-Type", "application/json")
        .formParam("name", "my-new-product-name")
    )
    .exec(
      http("getProductById")
        .get("/products")
        .queryParam("productId", 1)
    )
    .exec(
      http("deleteProduct")
        .delete("/products")
        .queryParam("productId", 1)
    )

  setUp(
    theScenarioBuilder.inject(atOnceUsers(1))
  ).protocols(theHttpProtocolBuilder)
}