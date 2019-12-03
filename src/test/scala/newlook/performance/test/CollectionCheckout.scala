package newlook.performance.test

import java.io.{InputStream, FileInputStream}
import io.gatling.commons.stats.assertion.{Count, ResponseTime, AssertionValidator}
import io.gatling.core.Predef._
import io.gatling.core.assertion.AssertionWithPathAndTarget
import io.gatling.core.structure.PopulationBuilder
import newlook.performance.config
import newlook.performance.scalaObjects.navigation_Pages._
import org.junit.After
import scala.language.postfixOps

/**
 * Created by sreekanth.bongunuri on 27/09/16.
 */
class CollectionCheckout extends Simulation{

/** Setup Users and ramp time **/
val perfTestUsers = config.Config.totalUsers
val perfTestSeconds = config.Config.rampOverSeconds
val perfCheckoutUsers = config.Config.totalCheckoutUsers
val perfTestRepeat = config.Config.repeatCount
val perfMeanResponseTime = config.Config.meanResponseTime
val perfMaxResponseTime = config.Config.maxResponseTime
val perfSuccessfulRequestsPercent = config.Config.successfulRequestsPercent
val perfLeastResponseTime = config.Config.leastResponseTime

val checkoutScenario =
scenario("checkout")
.exec(homePage.homepage,search.searchForCollection, pdpPage.pdpPage, addProductToTheCart.addProductToTheCart,navigateToTheLoginPage.navigateToLoginPage,loginPage.loginPage,checkoutPage.collectionCheckout)


private val inject: PopulationBuilder = checkoutScenario.inject(rampUsers(perfCheckoutUsers) over (perfTestSeconds))
private val protocols: PopulationBuilder = inject.protocols(config.Config.httpProtocol)

private val setUp: SetUp = setUp(protocols)

val a = setUp


}
