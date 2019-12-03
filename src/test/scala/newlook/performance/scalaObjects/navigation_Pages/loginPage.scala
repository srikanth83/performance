package newlook.performance.scalaObjects.navigation_Pages

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import newlook.performance.config.headers._
import newlook.performance.config.Config
/**
 * Created by sreekanth.bongunuri on 28/06/16.
 */
object loginPage {

 // def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString

  val randomString = new scala.util.Random(31)


var uri = Config.baseURL;

  pause(8)
  val loginPage = exec(http("login")
    .get("/fr/login/checkout")
    .headers(headers_0))
    .exec(http("LoginPage")
      .post("https://" + uri + ":443/fr/login/checkout/guest")
      .headers(headers_63)
      .formParam("email", "srbongunuri@salmon.com")
      .formParam("CSRFToken", "${CSRFToken}")
      .resources(http("request_64")
        .get("https://" + uri + ":443/fr/json/address/getAddressFormat.json?countryIsocode=FR&type=shipping")
        .headers(headers_64),
        http("request_66")
          .get("https://" + uri + ":443/fr/json/address/getAddressFormat.json?countryIsocode=FR&type=shipping")
          .headers(headers_64),
        http("request_67")
          .get("https://" + uri + ":443/fr/json/cart/currentCart.json")
          .headers(headers_64),
        http("request_68")
          .get("https://" + uri + ":443/json/country/countrySelectorData")
          .headers(headers_64),
        http("request_69")
          .get("https://" + uri + ":443/fr/json/paymentMethod/cardPaymentMethodData.json")
          .headers(headers_64),
        http("request_70")
          .get("https://" + uri + ":443/fr/json/address/getAddresses.json?billingAddress=true&shippingAddress=false")
          .headers(headers_64),
        http("request_71")
          .get("https://" + uri + ":443/fr/json/checkout/deliveryMode/supportedDeliveryModes")
          .headers(headers_64),
        http("request_72")
          .post("https://" + uri + ":443/fr/json/checkout/deliveryTextUpdates/setDeliverTextUpdates.json")
          .headers(csrfHeader)
          .headers(headers_72)
          .body(StringBody("{\"deliver\":true}")),
        http("request_73")
          .get("https://" + uri + ":443/fr/json/country/countrySelectorData")
          .headers(headers_64)))

  pause(10)
  val login = exec(http("request_2")
      .post("/fr/j_spring_security_check")
      .formParam("j_username", "srbongunuri")
      .formParam("j_password", "12341234")
      .formParam("CSRFToken", "${CSRFToken}").check(status.is(200))
      .resources(http("request_3")
        .get("/fr/json/cart/currentMiniCart.json")
        .headers(headers_1)))

    pause(10)

  val logout = exec(http("logout")
    .get("/fr/logout").check(status.is(200)))


  pause(42)
   var register = exec(http("Submit_Registration_Form")
       .post("https://" + uri + ":443/fr/register")
       .headers(headers_63)
       .formParam("CSRFToken", "${CSRFToken}")
       .formParam("email", "${CSRFToken}"+"@salmon.com")
       .formParam("pwd", "Pa55word")
       .formParam("titleCode", "mr")
       .formParam("firstName", "abcde")
       .formParam("lastName", "alskdfa")
       .formParam("genderCode", "MALE")
       .formParam("dayOfBirth", "12")
       .formParam("monthOfBirth", "1")
       .formParam("yearOfBirth", "1985")
       .formParam("mobileNumber", "1234567"))
         pause(40)
}
