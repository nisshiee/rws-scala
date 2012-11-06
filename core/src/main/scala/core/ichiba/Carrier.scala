package rwsscala.ichiba

sealed trait Carrier

object Carrier {

  case object PC extends Carrier
  case object Mobile extends Carrier
  case object SP extends Carrier

}
