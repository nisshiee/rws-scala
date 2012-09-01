package jp.co.rakuten.webservice

sealed trait ShipOversea extends Paramater
case object ShipOverseaAll extends ShipOversea {
  def param = Seq()
}
case class OnlyOversea(area: OverseaArea) extends ShipOversea {
  def param = Seq(
     "shipOverseasFlag" -> "1"
    ,"shipOverseasArea" -> area.code
  )
}

trait ShipOverseas {

  implicit def area2shipOversea(area: OverseaArea): ShipOversea = OnlyOversea(area)
}
