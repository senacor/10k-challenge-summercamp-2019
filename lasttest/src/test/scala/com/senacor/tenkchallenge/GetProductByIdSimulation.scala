package com.senacor.tenkchallenge

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder

/**
  * Example Gatling load test that sends one HTTP GET requests to a URL.
  * Note that the request is redirected and this causes the request count to become two.
  * Run this simulation with:
  * mvn -Dgatling.simulation.name=HttpSimulation1 gatling:execute
  *
  * @author Ivan Krizsan
  */
class GetProductByIdSimulation extends Simulation {
  /* Place for arbitrary Scala code that is to be executed before the simulation begins. */
  before {
    println("***** Simulation GetProductById is about to begin! *****")
  }

  /* Place for arbitrary Scala code that is to be executed after the simulation has ended. */
  after {
    println("***** Simulation GetProductById has ended! ******")
  }

  /*
   * A HTTP protocol builder is used to specify common properties of request(s) to be sent,
   * for instance the base URL, HTTP headers that are to be enclosed with all requests etc.
   */
  val theHttpProtocolBuilder = http
    .baseUrl("http://localhost:8080")

  /*
   * A scenario consists of one or more requests. For instance logging into a e-commerce
   * website, placing an order and then logging out.
   * One simulation can contain many scenarios.
   */
  /* Scenario1 is a name that describes the scenario. */
  val theScenarioBuilder = scenario("Produkt 1 abrufen")
    .exec(
      /* myRequest1 is a name that describes the request. */
      http("getProductById")
        .get("/product/1")
    )

  /*
   * Define the load simulation.
   * Here we can specify how many users we want to simulate, if the number of users is to increase
   * gradually or if all the simulated users are to start sending requests at once etc.
   * We also specify the HTTP protocol builder to be used by the load simulation.
   */
  setUp(
    theScenarioBuilder.inject(atOnceUsers(100))
  ).protocols(theHttpProtocolBuilder)
}