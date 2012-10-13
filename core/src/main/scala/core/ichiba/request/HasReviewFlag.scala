package rwsscala.ichiba

import rwsscala.util._

sealed trait HasReviewFlag extends Parameter {

  import HasReviewFlag._

  def int: Int
  def param = this match {
    case All => Seq()
    case _ => Seq("hasReviewFlag" -> int.toString)
  }
}

object HasReviewFlag {

  case object All extends HasReviewFlag {
    val int = 0
  }
  case object OnlyHave extends HasReviewFlag {
    val int = 1
  }
}
