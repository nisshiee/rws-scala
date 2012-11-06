package rwsscala.ichiba

sealed trait OrFlag

object OrFlag {

  case object And extends OrFlag
  case object Or extends OrFlag
}
