package rwsscala.ichiba

import org.specs2._

class OrFlagSpec extends Specification { def is =

  "OrFlag"                                                                                          ^
    "param"                                                                                         ^
      "AndSearchの場合Seq()が返る"                                                                  ! e1^
      "OrSearchの場合Seq(\"orFlag\" -> \"1\")が返る"                                                ! e2^
                                                                                                    end

  def e1 = AndSearch.param must beEmpty
  def e2 = OrSearch.param must equalTo(Seq("orFlag" -> "1"))
}
