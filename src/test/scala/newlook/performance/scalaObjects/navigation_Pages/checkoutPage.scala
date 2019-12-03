package newlook.performance.scalaObjects.navigation_Pages

import io.gatling.commons.util.TypeHelper.TypeValidator
import io.gatling.core.Predef._
import io.gatling.core.session
import io.gatling.http.Predef._
import io.gatling.jsonpath.JsonPath
import io.gatling.redis.feeder.RedisFeeder
import newlook.performance.config.headers._
import newlook.performance.config.Config


/**
 * Created by sreekanth.bongunuri on 28/06/16.
 */
object checkoutPage {

  var uri = Config.baseURL;
  val uuid = scala.math.abs(java.util.UUID.randomUUID.getMostSignificantBits).toString
  val jsonPost = "{\"sourceLatitude\":48.856614,\"carrierServiceName\":\"5087594\",\"available\":true,\"locationLatitude\":48.852192,\"locationAddress\":{\"country\":{\"isocode\":\"FR\",\"deliverable\":false},\"postalCode\":\"75006\",\"visibleInAddressBook\":false,\"line1\":\"118 BOULEVARD SAINT GERMAIN\",\"town\":\"PARIS\",\"restricted\":false,\"shippingAddress\":false,\"billingAddress\":false,\"defaultAddress\":false},\"formattedCollectionFromDate\":\"Tue 4 Oct\",\"storeId\":\"756250\",\"storePhoneNumber\":\"\",\"carrierServiceCode\":\"wnCollect FR\",\"distanceInKm\":0.9818762086678203,\"uid\":\"b2ccd46f-41b0-4ac3-a927-00f7aa9caaf0\",\"storeOpeningTimes\":[{\"formattedWeekDay\":\"Monday\",\"closed\":false,\"openingTimes\":[\"09:00-19:30\"]},{\"formattedWeekDay\":\"Tuesday\",\"closed\":false,\"openingTimes\":[\"09:00-19:30\"]},{\"formattedWeekDay\":\"Wednesday\",\"closed\":false,\"openingTimes\":[\"09:00-19:30\"]},{\"formattedWeekDay\":\"Thursday\",\"closed\":false,\"openingTimes\":[\"09:00-19:30\"]},{\"formattedWeekDay\":\"Friday\",\"closed\":false,\"openingTimes\":[\"09:00-19:30\"]},{\"formattedWeekDay\":\"Saturday\",\"closed\":false,\"openingTimes\":[\"09:00-17:00\"]},{\"formattedWeekDay\":\"Sunday\",\"closed\":true}],\"sourceLongitude\":2.3522219000000177,\"distanceInMiles\":0.6101084197799235,\"carrierCode\":\"WNC-01\",\"originalDeliveryOptionData\":{\"distanceUnit\":\"M\",\"sourceLatitude\":48.856614,\"carrierServiceName\":\"5087594\",\"deliveryToDate\":1475625599000,\"locationLatitude\":48.852192,\"available\":true,\"fullName\":\"5087594\",\"deliveryFromDate\":1475539200000,\"locationAddress\":{\"country\":{\"isocode\":\"FR\",\"deliverable\":false},\"postalCode\":\"75006\",\"visibleInAddressBook\":false,\"line1\":\"118 BOULEVARD SAINT GERMAIN\",\"town\":\"PARIS\",\"restricted\":false,\"shippingAddress\":false,\"billingAddress\":false,\"defaultAddress\":false},\"storeId\":\"756250\",\"storePhoneNumber\":\"\",\"carrierServiceCode\":\"wnCollect FR\",\"uid\":\"b2ccd46f-41b0-4ac3-a927-00f7aa9caaf0\",\"storeOpeningTimes\":[{\"weekDay\":\"MONDAY\",\"openingTimes\":[\"09:00-19:30\"]},{\"weekDay\":\"TUESDAY\",\"openingTimes\":[\"09:00-19:30\"]},{\"weekDay\":\"WEDNESDAY\",\"openingTimes\":[\"09:00-19:30\"]},{\"weekDay\":\"THURSDAY\",\"openingTimes\":[\"09:00-19:30\"]},{\"weekDay\":\"FRIDAY\",\"openingTimes\":[\"09:00-19:30\"]},{\"weekDay\":\"SATURDAY\",\"openingTimes\":[\"09:00-17:00\"]},{\"weekDay\":\"SUNDAY\"}],\"sourceLongitude\":2.3522219000000177,\"locationProviderId\":\"02b8ecb0-643e-4a48-92ab-c5e9ac2b02c4\",\"distanceValue\":981.8762086678204,\"carrierCode\":\"WNC-01\",\"locationLongitude\":2.34061,\"hasDisabledAccess\":false,\"storeName\":\"BUREAU DE POSTE PARIS ODEON BEAUX ARTS\",\"retentionPolicy\":\"Orders will be held for 10 working days\"},\"locationLongitude\":2.34061,\"hasDisabledAccess\":false,\"storeName\":\"BUREAU DE POSTE PARIS ODEON BEAUX ARTS\",\"retentionPolicy\":\"Orders will be held for 10 working days\",\"deliveryCost\":{\"currencyIso\":\"EUR\",\"priceType\":\"BUY\",\"formattedValue\":\"€3.00\",\"value\":3}}"


  pause(24)
  val checkoutPage = exec(http("request_74")
    .post("https://" + uri + ":443/fr/json/address/editAddress.json")
    .headers(headers_75).check(jsonPath("$.data.id").saveAs("id"))
    .body(StringBody("{\"country\":\"FR\",\"titleCode\":\"mr\",\"firstName\":\"french\",\"lastName\":\"user\",\"line1\":\"porte belle\",\"line2\":\"akslfdja\",\"postalCode\":\"12345\",\"town\":\"paris\",\"shippingAddress\":true,\"billingAddress\":true}"))
    .resources(http("request_75")
      .post("https://" + uri + ":443/fr/json/address/setDeliveryAddress.json")
      .headers(headers_75)
      .body(StringBody("{\"id\":\"${id}\"}")),
      http("request_76")
        .get("https://" + uri + ":443/fr/json/cart/currentCart.json")
        .headers(headers_64).check(jsonPath("$.data.paymentAddress.id").saveAs("billingAddressCode")),
      http("request_77")
        .get("https://" + uri + ":443/fr/json/address/getAddresses.json?billingAddress=false&shippingAddress=true")
        .headers(headers_64),
      http("request_78")
        .get("https://" + uri + ":443/fr/json/address/getAddresses.json?billingAddress=true&shippingAddress=false")
        .headers(headers_64),
         http("sop_request")
        .post("https://" + uri + ":443/fr/json/checkout/sop/sopRequestData.json")
           .headers(headers_6).check(jsonPath("$.success").is("true"),
           jsonPath("$").findAll.saveAs("response"),
           jsonPath("$.data.parameters.signature").findAll.saveAs("signature"),
      //     jsonPath("$.data.parameters.transaction_uuid").findAll.saveAs("transatcion_uuid"),
           jsonPath("$.data.parameters.reference_number").findAll.saveAs("reference_number"),
           jsonPath("$.data.parameters.access_key").findAll.saveAs("access_key"),
           jsonPath("$.data.parameters.signed_date_time").findAll.saveAs("signed_date_time"))
           .body(StringBody("{\"billingAddressCode\":\"${id}\"}")),
      http("request_109")
        .get("/fr/json/checkout/deliveryMode/supportedDeliveryModes")
        .headers(headers_10))).
     exec(session => {
    val signature = session("signature").as[Vector[String]].get(0)
  //  val transaction_uuid = session("transaction_uuid").as[Vector[String]].get(0)
    val reference_number = session("reference_number").as[Vector[String]].get(0)
    val access_key = session("access_key").as[Vector[String]].get(0)
    val signed_date_time = session("signed_date_time").as[Vector[String]].get(0)
       val response = session("response").as[Vector[String]].get(0)

    session.set("signature", signature)
  //  session.set("transaction_uuid", transaction_uuid)
    session.set("reference_number", reference_number)
    session.set("access_key", access_key)
    session.set("signed_date_time", signed_date_time)

    println("The response---->" +response)
    println("The signature value--->" + signature)
//    println("The uuid---->" +transaction_uuid)
    println("The reference_number---->" +reference_number)
    println("The access_key---->" +access_key)
    println("The signed_date_time---->" +signed_date_time)

    session
  })




  var createToken = exec(http("request_1")
  .post("https://testsecureacceptance.cybersource.com:443/silent/token/create")
  .headers(headers_0)
  .formParam("unsigned_field_names", "card_type,card_expiry_date,card_number,submit,merchant_defined_data90,card_expiry_year,card_expiry_month")
  .formParam("bill_to_address_postal_code", "75045")
  .formParam("signature","${signature}")
  .formParam("transaction_uuid", uuid)
  .formParam("transaction_type", "create_payment_token")
  .formParam("locale", "en")
  .formParam("bill_to_email", "srbongunuri@salmon.com")
  .formParam("signed_field_names", "access_key,bill_to_address_city,bill_to_address_country,bill_to_address_line1,bill_to_address_line2,bill_to_address_postal_code,bill_to_email,bill_to_forename,bill_to_surname,currency,customer_ip_address,locale,override_custom_cancel_page,override_custom_receipt_page,payment_method,profile_id,reference_number,signed_date_time,signed_field_names,skip_decision_manager,transaction_type,transaction_uuid,unsigned_field_names")
  .formParam("reference_number", "${reference_number}")
  .formParam("skip_decision_manager", "true")
  .formParam("customer_ip_address", "10.0.0.38")
  .formParam("bill_to_address_country", "FR")
  .formParam("bill_to_surname", "kjsalfj")
  .formParam("bill_to_address_line2", "")
  .formParam("bill_to_address_line1", "kjlsafjlas")
  .formParam("profile_id", "new_look_card_tokenisation_sop")
  .formParam("access_key", "${access_key}")
  .formParam("override_custom_receipt_page", "https://ta01.nlk374.neoworks.co.uk/fr/checkout/single/sop-response")
  .formParam("bill_to_address_city", "kjafaf")
  .formParam("currency", "EUR")
  .formParam("bill_to_forename", "kjafhds")
  .formParam("payment_method", "card")
  .formParam("override_custom_cancel_page", "https://ta01.nlk374.neoworks.co.uk/fr/checkout/single/sop-response")
  .formParam("signed_date_time", "${signed_date_time}")
  .formParam("merchant_defined_data90", "true")
  .formParam("card_type", "001")
  .formParam("card_number", "4111111111111111")
  .formParam("card_expiry_month", "string:10")
  .formParam("card_expiry_year", "number:2019")
  .formParam("card_expiry_date", "10-2019")
  .formParam("save-card-details", "on")
  .formParam("submit", "Use this card")
  .resources(http("request_2")
    .post("/fr/checkout/single/sop-response")
    .headers(headers_0)))
  .pause(10)


  var collectionCheckout = exec(http("selecting_collection_request")
        .post("https://" + uri + ":443/fr/json/checkout/deliveryMode/collection")
        .headers(headers_45)
        .body(StringBody("{}")))
    .pause(4)
    .exec(http("search_for_collection_location")
      .get("https://" + uri + ":443/fr/json/collectionLocation/get.json?latitude=48.856614&longitude=2.3522219000000177"))
    .pause(1)
    .exec(http("collection_location")
      .post("https://" + uri + ":443/fr/json/collectionLocation/select.json")
      .headers(headers_45)
      .body(StringBody(jsonPost)))
    .pause(8)
    .exec(http("your_details_from_for_collection")
      .post("https://" + uri + ":443/fr/json/collectionLocation/setOrderCollector.json")
      .headers(headers_45)
      .body(StringBody("{\"titleCode\":\"rev\",\"firstName\":\"adf\",\"lastName\":\"akfdja\"}"))
      .resources(http("request_8")
        .get("https://" + uri + ":443/fr/json/checkout/cv2/validateCv2.json")))
    .pause(2)
    .exec(http("request_9")
      .post("https://" + uri + ":443/fr/json/checkout/deliveryTextUpdates/setDeliverTextUpdates.json")
      .headers(headers_45)
      .body(StringBody("{\"deliver\":false}"))
      .resources(http("cv2_validation_1")
        .get("https://" + uri + ":443/fr/json/checkout/cv2/validateCv2.json")))
    .pause(17)
    .exec(http("edit_json_address")
      .post("https://" + uri + ":443/fr/json/address/editAddress.json")
      .headers(headers_45).check(jsonPath("$.data.id").saveAs("id"))
      .body(StringBody("{\"country\":\"FR\",\"titleCode\":\"rev\",\"firstName\":\"asdfh\",\"lastName\":\"sajfa\",\"line1\":\"afdj\",\"postalCode\":\"12345\",\"town\":\"asdfa\",\"shippingAddress\":true,\"billingAddress\":true}"))
      .resources(http("set_billing_address")
        .post("https://" + uri + ":443/fr/json/address/setBillingAddress.json")
        .headers(headers_45)
        .body(StringBody("{\"id\":\"${id}\"}")),
        http("setting_billing_and_shipping_address")
          .get("https://" + uri + ":443/fr/json/address/getAddresses.json?billingAddress=true&shippingAddress=false"),
        http("validating_cv2_again")
          .get("https://" + uri + ":443/fr/json/checkout/cv2/validateCv2.json"),
        http("request_16")
          .get("https://" + uri + ":443/fr/json/cart/currentCart.json"),
        http("request_17")
          .get("https://" + uri + "/fr/json/address/getAddresses.json?billingAddress=false&shippingAddress=true")))

}
