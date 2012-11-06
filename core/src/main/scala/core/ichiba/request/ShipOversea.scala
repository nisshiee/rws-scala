package rwsscala.ichiba

sealed trait ShipOversea

object ShipOversea {

  case object All extends ShipOversea
  case class OnlyAccept(area: OverseaArea) extends ShipOversea
}

trait ShipOverseas {

  implicit def area2shipOversea(area: OverseaArea): ShipOversea = ShipOversea.OnlyAccept(area)
}
