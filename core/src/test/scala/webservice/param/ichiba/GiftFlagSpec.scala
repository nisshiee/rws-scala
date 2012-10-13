package rwsscala.param.ichiba

import org.specs2._

class GiftFlagSpec extends Specification { def is =

  "GiftFlag"                                                                                        ^
    "param"                                                                                         ^
      "GiftAllの場合Seq()が返る"                                                                    ! e1^
      "OnlyAllowGiftの場合Seq(\"giftFlag\" -> \"1\")が返る"                                         ! e2^
                                                                                                    end

  def e1 = GiftAll.param must beEmpty
  def e2 = OnlyAllowGift.param must equalTo(Seq("giftFlag" -> "1"))
}
