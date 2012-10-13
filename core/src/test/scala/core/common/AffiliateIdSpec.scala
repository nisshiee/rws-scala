package rwsscala

import rwsscala.Implicits._
import org.specs2._

class AffiliateIdSpec extends Specification { def is =

  "AffiliateId"                                                                 ^
    "param"                                                                     ^
      "optがSomeの場合Seq(\"affiliateId\" -> value)が返る"                      ! e1^
      "optがNoneの場合Seq()が返る"                                              ! e2^
                                                                                p^
    "str2affiliateId"                                                           ^
      "AffiliateId(Some(value))にimplicit conversionされる"                     ! e3^
                                                                                end

  def e1 = AffiliateId(Some("affid")).param must equalTo(Seq("affiliateId" -> "affid"))
  def e2 = AffiliateId(None).param must beEmpty

  def e3 = {
    val aff: AffiliateId = "affid"
    aff must equalTo(AffiliateId(Some("affid")))
  }

}
