package jp.co.rakuten.webservice

import org.specs2._

class HasReviewFlagSpec extends Specification { def is =

  "HasReviewFlag"                                                                                   ^
    "param"                                                                                         ^
      "HasReviewAllの場合Seq()が返る"                                                               ! e1^
      "OnlyHasReviewの場合Seq(\"hasReviewFlag\" -> \"1\")が返る"                                    ! e2^
                                                                                                    end

  def e1 = HasReviewAll.param must beEmpty
  def e2 = OnlyHasReview.param must equalTo(Seq("hasReviewFlag" -> "1"))
}
