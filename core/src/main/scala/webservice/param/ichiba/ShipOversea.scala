package jp.co.rakuten.webservice.param.ichiba

import jp.co.rakuten.webservice.util._

sealed trait ShipOversea extends Parameter
case object ShipOverseaAll extends ShipOversea {
  def param = Seq()
}
case class OnlyOversea(area: OverseaArea) extends ShipOversea {
  def param = area match {
    case AllCountry => Seq("shipOverseasFlag" -> "1")
    case _ => Seq(
       "shipOverseasFlag" -> "1"
      ,"shipOverseasArea" -> area.code
    )
  }
}

trait ShipOverseas {

  implicit def area2shipOversea(area: OverseaArea): ShipOversea = OnlyOversea(area)
}
