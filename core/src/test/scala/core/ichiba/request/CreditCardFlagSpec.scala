package rwsscala.ichiba

import org.specs2._

import rwsscala.util._

class CreditCardFlagSpec extends Specification { def is =

  "CreditCardFlag"                                                                                  ^
    "param"                                                                                         ^
      "Allの場合Seq()が返る"                                                                        ! e1^
      "OnlyAcceptの場合Seq(\"creditCardFlag\" -> \"1\")が返る"                                      ! e2^
                                                                                                    end

  import CreditCardFlag._
  import ItemSearchParameters._

  def e1 = (All: CreditCardFlag).param must beEmpty
  def e2 = (OnlyAccept: CreditCardFlag).param must equalTo(Seq("creditCardFlag" -> "1"))
}
