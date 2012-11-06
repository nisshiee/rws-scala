package rwsscala.ichiba

sealed trait HasReviewFlag

object HasReviewFlag {

  case object All extends HasReviewFlag
  case object OnlyHave extends HasReviewFlag
}
