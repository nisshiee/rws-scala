package rwsscala.ichiba

import org.specs2._

import rwsscala.util._

class HasReviewFlagSpec extends Specification { def is =

  "HasReviewFlag"                                                                                   ^
    "param"                                                                                         ^
      "Allの場合Seq()が返る"                                                                        ! e1^
      "OnlyHaveの場合Seq(\"hasReviewFlag\" -> \"1\")が返る"                                         ! e2^
                                                                                                    end

  import HasReviewFlag._
  import ItemSearchParameters._

  def e1 = (All: HasReviewFlag).param must beEmpty
  def e2 = (OnlyHave: HasReviewFlag).param must equalTo(Seq("hasReviewFlag" -> "1"))
}
