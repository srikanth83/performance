package newlook.performance.scalaObjects.navigation_Pages

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import newlook.performance.config.headers._
import newlook.performance.config.Config
/**
 * Created by sreekanth.bongunuri on 28/06/16.
 */
object plpPage {

  var uri = Config.baseURL;

  pause(2)
  val plpPage = exec(http("plpPage")
    .get("/fr/UK-Womens-Department/c/uk-womens")
    .headers(headers_0)
    .resources(http("request_48")
      .get(uri + "/newlookretailers/sandbox/serverComponent.php?r=439.1497183251629&ClientID=1905&PageID=http%3A%2F%2Fta01.nlk374.neoworks.co.uk%2Ffr%2FFR-Womens-Department%2Fc%2Ffr-womens%3Fpage_template%3DProductGridPageTemplate%26page_template_type%3Dmain%26currency_conversion%3D0.79%26country%3DFR%26label%3Dundefined")
      .headers(headers_3),
      http("request_49")
        .get("/fr/json/cart/currentMiniCart.json")
        .headers(headers_43)))
    .pause(1)
    .exec(http("request_50")
      .get(uri + "/newlookretailers/sandbox/code/585ddca051f39dfb3c3828cc1ae57815.js?conditionId0=424893")
      .headers(headers_3))
    .pause(1)
    .exec(http("request_51")
      .get(uri + "/error/e.gif?msg=Invalid%20data%20definition%20used%3A%20NaN&lnn=63&fn=http%3A%2F%2Fnexus.ensighten.com%2Fnewlookretailers%2Fsandbox%2FBootstrap.js&cid=1905&client=newlookretailers&publishPath=sandbox&rid=-1&did=-1&errorName=DataDefinitionException"))
    .pause(6)
    .exec(http("request_52")
      .get("/fr/FR-Womens-Department/c/fr-womens.json?currency=EUR&language=en&page=0&q=:price-asc&sort=price-asc&text=")
      .headers(headers_43))
    .pause(2)
    .exec(http("request_54")
        .get("/fr/FR-Womens-Department/c/fr-womens.json?currency=EUR&language=en&page=0&q=:price-asc:colourGroupCode:40&sort=price-asc&text=")
        .headers(headers_43))
    .pause(3)
    .exec(http("request_56")
      .get("/fr/FR-Womens-Department/c/fr-womens.json?currency=EUR&language=en&page=0&q=:price-asc:colourGroupCode:40:category:fr-women-dresses&sort=price-asc&text=")
      .headers(headers_43))
    .pause(2)
    .exec(http("request_57")
      .get("/fr/FR-Womens-Department/c/fr-womens.json?currency=EUR&language=en&page=0&q=:price-asc:colourGroupCode:40:category:fr-women-dresses:displaySize:12&sort=price-asc&text=")
      .headers(headers_43))


}
