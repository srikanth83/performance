package newlook.performance.scalaObjects.navigation_Pages

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import newlook.performance.config.headers._


/**
 * Created by sreekanth.bongunuri on 28/06/16.
 */
object navigateToTheLoginPage {

  pause(3)
  val navigateToLoginPage = exec(http("NavigateToLoginPage")
    .get("/fr/cart/checkout")
    .headers(headers_0).check(status is (200)))


}
