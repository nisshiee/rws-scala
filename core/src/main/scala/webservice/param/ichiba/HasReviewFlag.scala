package jp.co.rakuten.webservice.param.ichiba

import jp.co.rakuten.webservice.util._

sealed trait HasReviewFlag extends Parameter {
  def int: Int
  def param = this match {
    case HasReviewAll => Seq()
    case _ => Seq("hasReviewFlag" -> int.toString)
  }
}
case object HasReviewAll extends HasReviewFlag {
  val int = 0
}
case object OnlyHasReview extends HasReviewFlag {
  val int = 1
}
