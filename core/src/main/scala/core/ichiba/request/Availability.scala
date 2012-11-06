package rwsscala.ichiba

sealed trait Availability

object Availability {

  case object OnlyAvailable extends Availability
  case object All extends Availability
}
