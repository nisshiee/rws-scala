package jp.co.rakuten.webservice

sealed trait PointRate extends Parameter
case object PointRateAll extends PointRate {
  def param = Seq()
}
case class OnlyPointUp(rateOpt: Option[Int]) extends PointRate {
  def param =
    Seq("pointRateFlag" -> "1") ++ rateOpt.map("pointRate" -> _.toString)
}

trait PointRates {

  implicit def int2pointRate: Int => PointRate = {
    case r if r <= 1 => OnlyPointUp(None)
    case r if r > 10 => OnlyPointUp(None)
    case r => OnlyPointUp(Some(r))
  }
}
