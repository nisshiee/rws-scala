package rwsscala.ichiba

import rwsscala.util._

sealed trait ShipOversea extends Parameter

object ShipOversea {

  case object All extends ShipOversea {
    def param = Seq()
  }
  case class OnlyAccept(area: OverseaArea) extends ShipOversea {
    def param = area match {
      case OverseaArea.AllCountry => Seq("shipOverseasFlag" -> "1")
      case _ => Seq(
         "shipOverseasFlag" -> "1"
        ,"shipOverseasArea" -> area.code
      )
    }
  }
}

trait ShipOverseas {

  implicit def area2shipOversea(area: OverseaArea): ShipOversea = ShipOversea.OnlyAccept(area)
}
