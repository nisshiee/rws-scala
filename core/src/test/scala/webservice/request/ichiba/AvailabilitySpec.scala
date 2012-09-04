package jp.co.rakuten.webservice

import org.specs2._

class AvailabilitySpec extends Specification { def is =

  "Availability"                                                                                    ^
    "param"                                                                                         ^
      "Availableの場合Seq()が返る"                                                                  ! e1^
      "Allの場合Seq(\"availability\" -> \"0\")が返る"                                               ! e2^
                                                                                                    end

  def e1 = Available.param must beEmpty
  def e2 = All.param must equalTo(Seq("availability" -> "0"))
}
