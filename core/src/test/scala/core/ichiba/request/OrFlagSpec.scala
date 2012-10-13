package rwsscala.ichiba

import org.specs2._

class OrFlagSpec extends Specification { def is =

  "OrFlag"                                                                                          ^
    "param"                                                                                         ^
      "Andの場合Seq()が返る"                                                                        ! e1^
      "Orの場合Seq(\"orFlag\" -> \"1\")が返る"                                                      ! e2^
                                                                                                    end

  import OrFlag._

  def e1 = And.param must beEmpty
  def e2 = Or.param must equalTo(Seq("orFlag" -> "1"))
}
