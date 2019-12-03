package newlook.performance.scalaObjects.navigation_Pages


import io.gatling.core.Predef._
import io.gatling.http.Predef._
import newlook.performance.config.headers._
import newlook.performance.config.Config

/**
 * Created by sreekanth.bongunuri on 01/07/16.
 */
object storeLocator {

  var uri = Config.baseURL;

  val storeLocatorNavigation = feed(csv("storeCoordinates.csv").circular)
    .exec(http("storeLocator")
      .get("/fr/store-finder")
      .headers(headers_0)
      .resources(http("request_22")
      .get("https://" + uri + ":443/fr/json/store-finder/getCountries.json")
      .headers(headers_45),
      http("request_46")
        .get("https://" + uri + ":443/fr/json/cart/currentMiniCart.json")
        .headers(headers_45)))
    .pause(5)
    .exec(http("request_49")
      .get("https://" + uri + ":443/store-finder/search?latitude=${latitude}&longitude=${longitude}&address=${address}&region=${region}")
      .headers(headers_63))
    .pause(1)
    .exec(http("request_50")
      .get("/fr/json/store-finder/getCountries.json?currentCountryIsocode=${region}")
      .headers(headers_43)
      .resources(http("request_51")
        .get("/fr/json/store-finder/getStores.json?countryCode=${region}&latitude=${latitude}&longitude=${longitude}&searchText=${address}")
        .headers(headers_43),
        http("request_52")
          .get("/fr/json/cart/currentMiniCart.json")
          .headers(headers_43)))

}
