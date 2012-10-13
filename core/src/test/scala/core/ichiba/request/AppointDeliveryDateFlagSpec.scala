package rwsscala.ichiba

import org.specs2._

class AppointDeliveryDateFlagSpec extends Specification { def is =

  "AppointDeliveryDateFlag"                                                                         ^
    "param"                                                                                         ^
      "Allの場合Map()が返る"                                                                        ! e1^
      "OnlyAcceptの場合Seq(\"appointDeliveryDateFlag\" -> \"1\")が返る"                             ! e2^
                                                                                                    end

  def e1 = AppointDeliveryDateFlag.All.param must beEmpty
  def e2 = AppointDeliveryDateFlag.OnlyAccept.param must equalTo(Seq("appointDeliveryDateFlag" -> "1"))

}
