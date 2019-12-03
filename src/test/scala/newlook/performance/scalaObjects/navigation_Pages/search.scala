package newlook.performance.scalaObjects.navigation_Pages

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import newlook.performance.config.headers._
/**
 * Created by sreekanth.bongunuri on 28/06/16.
 */
object search {


  val search = feed(csv("search.csv").circular)
    .pause(3)
    .exec(http("SearchForTheProduct")
      //.get("/fr/search/autocomplete?text=${code}")
      //.resources(http("Product_Search_Request")
        .get("/fr/search/?text=${code}")
        .headers(headers_0))

  val multipleSearch = feed(csv("multiplesearch.csv").circular)
    .pause(3)
    .exec(http("SearchForTheProduct")
      //.get("/fr/search/autocomplete?text=${productCode}")
   //   .resources(http("Product_Search_Request")
        .get("/fr/search/?text=${productCode}")
        .headers(headers_0).check(status.is(200)))


  val searchForCollection = feed(csv("multiplesearch.csv").circular)
    .pause(3)
    .exec(http("SearchForTheProduct")
      //.get("/fr/search/autocomplete?text=${productCode}")
   //   .resources(http("Product_Search_Request")
        .get("/fr/search/?text=2270783")
      .headers(headers_0).check(status.is(200)))

}
