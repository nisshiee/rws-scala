package rwsscala.ichiba

import org.specs2._

class PurchaseTypeSpec extends Specification { def is =

  "PurchaseType"                                                                                    ^
    "param"                                                                                         ^
      "NormalOrderの場合Seq()が返る"                                                                ! e1^
      "StandingOrderの場合Seq(\"purchaseType\" -> \"1\")が返る"                                     ! e2^
      "DistributeOrderの場合Seq(\"purchaseType\" -> \"2\")が返る"                                   ! e3^
                                                                                                    end

  import PurchaseType._

  def e1 = NormalOrder.param must beEmpty
  def e2 = StandingOrder.param must equalTo(Seq("purchaseType" -> "1"))
  def e3 = DistributeOrder.param must equalTo(Seq("purchaseType" -> "2"))
}
