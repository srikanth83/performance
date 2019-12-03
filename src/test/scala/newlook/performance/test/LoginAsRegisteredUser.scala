package newlook.performance.test

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import newlook.performance.config
import newlook.performance.scalaObjects.navigation_Pages.{loginPage, homePage}

/**
 * Created by sreekanth.bongunuri on 05/07/16.
 */
class LoginAsRegisteredUser extends Simulation {

  /** Setup Users and ramp time **/
  val perfTestUsers = config.Config.totalUsers
  val perfTestSeconds = config.Config.rampOverSeconds
  val perfTestRepeat = config.Config.repeatCount
  val perfMeanResponseTime = config.Config.meanResponseTime
  val perfMaxResponseTime = config.Config.maxResponseTime
  val perfSuccessfulRequestsPercent = config.Config.successfulRequestsPercent
  val perfLeastResponseTime = config.Config.leastResponseTime


  val loginScenario = scenario("login&logout").exec(homePage.homepage,loginPage.login,loginPage.logout)

  setUp(loginScenario.inject(rampUsers(perfTestUsers) over(perfTestSeconds)).protocols(config.Config.httpProtocol))
}
