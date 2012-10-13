package rwsscala.ichiba

import org.specs2._

class AvailabilitySpec extends Specification { def is =

  "Availability"                                                                                    ^
    "param"                                                                                         ^
      "OnlyAvailableの場合Seq()が返る"                                                              ! e1^
      "Allの場合Seq(\"availability\" -> \"0\")が返る"                                               ! e2^
                                                                                                    end

  import Availability._

  def e1 = OnlyAvailable.param must beEmpty
  def e2 = All.param must equalTo(Seq("availability" -> "0"))
}
