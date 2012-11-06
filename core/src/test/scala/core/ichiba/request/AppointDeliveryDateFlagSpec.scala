package rwsscala.ichiba

import org.specs2._

import rwsscala.util._

class AppointDeliveryDateFlagSpec extends Specification { def is =

  "AppointDeliveryDateFlag"                                                                         ^
    "param"                                                                                         ^
      "Allの場合Map()が返る"                                                                        ! e1^
      "OnlyAcceptの場合Seq(\"appointDeliveryDateFlag\" -> \"1\")が返る"                             ! e2^
                                                                                                    end

  import AppointDeliveryDateFlag._
  import ItemSearchParameters._

  def e1 = (All: AppointDeliveryDateFlag).param must beEmpty
  def e2 = (OnlyAccept: AppointDeliveryDateFlag).param must equalTo(Seq("appointDeliveryDateFlag" -> "1"))

}
