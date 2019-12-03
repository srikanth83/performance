package newlook.performance.scalaObjects.navigation_Pages

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import newlook.performance.config.headers._


/**
 * Created by sreekanth.bongunuri on 28/06/16.
 */
object pdpPage {


  pause(2)
  val pdpPage = feed(csv("search.csv").circular)
    exec(http("PdpPage")
    .get("/fr/FR-Teens-Department/Swimwear/SEAHORSE-CROP-TOP-BIKINI/p/${code}${size}")
    .headers(headers_0).check((status.is(200)))
    .resources(http("request_39")
      .get("/fr/json/cart/potentialDeliveryPromotions.json?productCode=${code}${size}"),
      http("request_40")
        .get("/fr/json/product/productDetails.json?productCode=${code}${size}"),
      http("request_41")
        .get("/fr/json/deliveryMessaging/deliveryMessages.json"),
      http("request_42")
        .get("/fr/json/cart/currentMiniCart.json"),
      http("request_43")
        .get("/fr/json/deliveryMessaging/deliveryMessages.json")
        .headers(headers_3)))
}
