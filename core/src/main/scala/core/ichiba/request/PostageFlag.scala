package rwsscala.ichiba

sealed trait PostageFlag

object PostageFlag {

  case object All extends PostageFlag
  case object OnlyInclude extends PostageFlag
}
