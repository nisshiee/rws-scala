package rwsscala.ichiba

import org.specs2._

class HasReviewFlagSpec extends Specification { def is =

  "HasReviewFlag"                                                                                   ^
    "param"                                                                                         ^
      "Allの場合Seq()が返る"                                                                        ! e1^
      "OnlyHaveの場合Seq(\"hasReviewFlag\" -> \"1\")が返る"                                         ! e2^
                                                                                                    end

  import HasReviewFlag._

  def e1 = All.param must beEmpty
  def e2 = OnlyHave.param must equalTo(Seq("hasReviewFlag" -> "1"))
}
