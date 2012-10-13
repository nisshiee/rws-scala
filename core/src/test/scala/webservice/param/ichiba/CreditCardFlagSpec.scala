package rwsscala.param.ichiba

import org.specs2._

class CreditCardFlagSpec extends Specification { def is =

  "CreditCardFlag"                                                                                  ^
    "param"                                                                                         ^
      "CreditCardAllの場合Seq()が返る"                                                              ! e1^
      "OnlyAllowCreditCardの場合Seq(\"creditCardFlag\" -> \"1\")が返る"                             ! e2^
                                                                                                    end

  def e1 = CreditCardAll.param must beEmpty
  def e2 = OnlyAllowCreditCard.param must equalTo(Seq("creditCardFlag" -> "1"))
}
