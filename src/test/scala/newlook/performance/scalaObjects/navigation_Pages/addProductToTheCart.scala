package newlook.performance.scalaObjects.navigation_Pages

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import newlook.performance.config.headers._



/**
 * Created by sreekanth.bongunuri on 28/06/16.
 */
object addProductToTheCart {


  pause(3)
  val addProductToTheCart = feed(csv("search.csv").circular)
    .exec(http("SelectSizeandAddToTheCart")
    .post("/fr/json/cart/addToCart")
    .headers(csrfHeader)
    .headers(headers_45)
      .body(StringBody("{\"code\":\"192001248134\"}"))
    .resources(http("request_46")
      .get("/fr/json/deliveryMessaging/deliveryMessages.json"),
      http("request_47")
        .get("/fr/json/cart/potentialDeliveryPromotions.json?productCode=192001248")))
    .pause(1)
    .exec(http("NavigateToTheFullCartPage")
      .get("/fr/cart")
      .headers(headers_0)
      .resources(http("request_50")
        .get("/fr/json/cart/currentCart.json")
        .headers(headers_3),
        http("request_52")
          .get("/fr/json/deliveryMessaging/deliveryMessages.json")))



}
