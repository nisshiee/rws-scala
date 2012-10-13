package rwsscala.ichiba

import org.specs2._

class FieldSpec extends Specification { def is =

  "Field"                                                                                           ^
    "param"                                                                                         ^
      "StrictSearchの場合Seq()が返る"                                                               ! e1^
      "BroadSearchの場合Seq(\"field\" -> \"0\")が返る"                                              ! e2^
                                                                                                    end

  def e1 = StrictSearch.param must beEmpty
  def e2 = BroadSearch.param must equalTo(Seq("field" -> "0"))
}
