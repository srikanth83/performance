package newlook.performance.config

import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
 * Created by sreekanth.bongunuri on 28/06/16.
 */
object Config{

  val users = System.getProperty("users")
  val ramp = System.getProperty("ramp")
  val checkoutUsers = System.getProperty("checkoutUsers")

  val qa02:String = System.getProperty("qa02")
  val ta01 = "ta01.nlk374.neoworks.co.uk"
  val qa01 = "qa01.nlk374.neoworks.co.uk"

 // var baseURL:String = "qa02.nlk374.neoworks.co.uk"
  var baseURL:String = System.getProperty("siteURL")+".nlk374.neoworks.co.uk"
  var totalUsers =  users.toInt
  var rampOverSeconds =   ramp.toInt
  var totalCheckoutUsers = checkoutUsers.toInt
  var repeatCount:Int = 0
  var meanResponseTime:Int = 1000
  var leastResponseTime:Int = 0
  var maxResponseTime:Int = 2000
  var successfulRequestsPercent:Int = 95
  val http_status_ok:Int = 200

    println("Number of users" +users)
    println("ramp up time" +ramp)
    println("Number of checkoutUsers" +totalCheckoutUsers)

  val httpProtocol = http
    .baseURL("http://"+baseURL)
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
    .acceptHeader("application/json, text/plain, */*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0")


}
