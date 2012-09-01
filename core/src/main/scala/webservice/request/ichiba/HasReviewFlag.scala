package jp.co.rakuten.webservice

sealed trait HasReviewFlag extends Paramater {
  def int: Int
  def param = Seq("hasReviewFlag" -> int.toString)
}
case object HasReviewAll extends HasReviewFlag {
  val int = 0
}
case object OnlyHasReview extends HasReviewFlag {
  val int = 1
}
