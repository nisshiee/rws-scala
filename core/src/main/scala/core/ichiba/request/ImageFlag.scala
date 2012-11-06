package rwsscala.ichiba

sealed trait ImageFlag

object ImageFlag {

  case object All extends ImageFlag
  case object OnlyHave extends ImageFlag
}
