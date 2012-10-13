package rwsscala.ichiba

import org.specs2._

class CreditCardFlagSpec extends Specification { def is =

  "CreditCardFlag"                                                                                  ^
    "param"                                                                                         ^
      "Allの場合Seq()が返る"                                                                        ! e1^
      "OnlyAcceptの場合Seq(\"creditCardFlag\" -> \"1\")が返る"                                      ! e2^
                                                                                                    end

  import CreditCardFlag._

  def e1 = All.param must beEmpty
  def e2 = OnlyAccept.param must equalTo(Seq("creditCardFlag" -> "1"))
}
