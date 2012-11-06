package rwsscala.ichiba

import org.specs2._

import rwsscala.util._

class GiftFlagSpec extends Specification { def is =

  "GiftFlag"                                                                                        ^
    "param"                                                                                         ^
      "Allの場合Seq()が返る"                                                                        ! e1^
      "OnlyAcceptの場合Seq(\"giftFlag\" -> \"1\")が返る"                                            ! e2^
                                                                                                    end

  import GiftFlag._
  import ItemSearchParameters._

  def e1 = (All: GiftFlag).param must beEmpty
  def e2 = (OnlyAccept: GiftFlag).param must equalTo(Seq("giftFlag" -> "1"))
}
