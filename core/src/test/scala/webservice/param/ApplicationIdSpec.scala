package jp.co.rakuten.webservice.param

import jp.co.rakuten.webservice.Implicits._
import org.specs2._

class ApplicationIdSpec extends Specification { def is =

  "ApplicationId"                                                               ^
    "param"                                                                     ^
      "Seq(\"applicationId\" -> value)が返る"                                   ! e1^
                                                                                p^
    "str2applicationId"                                                         ^
      "ApplicationId(value)にimplicit conversionされる"                         ! e2^
                                                                                end

  def e1 = ApplicationId("appid").param must equalTo(Seq("applicationId" -> "appid"))

  def e2 = {
    val app: ApplicationId = "appid"
    app must equalTo(ApplicationId("appid"))
  }

}
