package jp.co.rakuten.webservice

import scalaz._, Scalaz._

sealed trait ShipOverseaStatus
case object DisableShipOversea extends ShipOverseaStatus

case class AcceptShipOversea(areas: List[OverseaArea]) extends ShipOverseaStatus

object ShipOverseaStatus {

  def parseAreasVld(str: String): Validation[ApiError, List[OverseaArea]] = {
    def error = JsonParseError("shipOverseasArea can't be parsed")
    if (str endsWith "/")
      error.failure
    else
      str
        .split('/')
        .toList
        .map(OverseaArea.parseOpt)
        .sequence[Option, OverseaArea]
        .toSuccess(error)
  }

  def parseVld(flag: Int, areas: String): Validation[ApiError, ShipOverseaStatus] = flag match {
    case 0 => DisableShipOversea.success
    case 1 => parseAreasVld(areas) ∘ AcceptShipOversea.apply
    case _ => JsonParseError("shipOverseasFlag must be in 0, 1").failure
  }
}
