package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {
	val uri1 = "https://www.google-analytics.com/g/collect"

	val httpProtocol = http
		.baseUrl("https://demo.nopcommerce.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en,vi-VN;q=0.9,vi;q=0.8")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")

	val headers_0 = Map(
		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
		"sec-ch-ua" -> """Not.A/Brand";v="8", "Chromium";v="114", "Google Chrome";v="114""",
		"sec-ch-ua-mobile" -> "?0",
		"sec-ch-ua-platform" -> "Windows",
		"sec-fetch-dest" -> "document",
		"sec-fetch-mode" -> "navigate",
		"sec-fetch-site" -> "same-origin",
		"sec-fetch-user" -> "?1",
		"upgrade-insecure-requests" -> "1")

	val headers_1 = Map(
		"origin" -> "https://demo.nopcommerce.com",
		"sec-ch-ua" -> """Not.A/Brand";v="8", "Chromium";v="114", "Google Chrome";v="114""",
		"sec-ch-ua-mobile" -> "?0",
		"sec-ch-ua-platform" -> "Windows",
		"sec-fetch-dest" -> "empty",
		"sec-fetch-mode" -> "no-cors",
		"sec-fetch-site" -> "cross-site")

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/login?returnUrl=%2F")
			.headers(headers_0))
		.pause(5)
		.exec(http("request_1")
			.post(uri1 + "?v=2&tid=G-SCT41TW89V&gtm=45je36l0&_p=369908329&cid=1916325760.1686557696&ul=en&sr=1920x1080&uaa=x86&uab=64&uafvl=Not.A%252FBrand%3B8.0.0.0%7CChromium%3B114.0.5735.134%7CGoogle%2520Chrome%3B114.0.5735.134&uamb=0&uam=&uap=Windows&uapv=10.0.0&uaw=0&ngs=1&_s=1&sid=1687688019&sct=3&seg=1&dl=https%3A%2F%2Fdemo.nopcommerce.com%2Flogin%3FreturnUrl%3D%252F&dr=https%3A%2F%2Fdemo.nopcommerce.com%2F&dt=nopCommerce%20demo%20store.%20Login&en=page_view&_ee=1")
			.headers(headers_1))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}