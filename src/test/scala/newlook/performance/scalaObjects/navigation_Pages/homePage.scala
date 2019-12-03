package newlook.performance.scalaObjects.navigation_Pages

/**
 * Created by sreekanth.bongunuri on 29/06/16.
 */

import java.net.URI

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.cookie.CookieJar
import newlook.performance.config.Config
import newlook.performance.config.headers._



object homePage {

  var uri = Config.baseURL;

  val homepage = exec(http("homePage")
    .get("/fr/")
    .headers(headers_3))
    .exec(session => {

      val cookieJar = session("gatling.http.cookies").as[CookieJar].get(org.asynchttpclient.uri.Uri.create("http://"+uri))
      val ck = cookieJar.find(_.getName == "XSRF-TOKEN")

      session.set("CSRFToken", ck.getOrElse(null).getValue)	})



}
