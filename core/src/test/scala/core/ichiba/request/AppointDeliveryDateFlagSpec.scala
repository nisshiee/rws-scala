package rwsscala.ichiba

import org.specs2._

class AppointDeliveryDateFlagSpec extends Specification { def is =

  "AppointDeliveryDateFlag"                                                                         ^
    "param"                                                                                         ^
      "AppointDeliveryDateAllの場合Map()が返る"                                                     ! e1^
      "OnlyAllowAppointDeliveryDateの場合Seq(\"appointDeliveryDateFlag\" -> \"1\")が返る"           ! e2^
                                                                                                    end

  def e1 = AppointDeliveryDateAll.param must beEmpty
  def e2 = OnlyAllowAppointDeliveryDate.param must equalTo(Seq("appointDeliveryDateFlag" -> "1"))

}
