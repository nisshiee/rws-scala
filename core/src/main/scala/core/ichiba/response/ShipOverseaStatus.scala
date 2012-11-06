package rwsscala.ichiba

import rwsscala._
import rwsscala.util._
import scalaz._, Scalaz._

sealed trait ShipOverseaStatus
case object DisableShipOversea extends ShipOverseaStatus

case class AcceptShipOversea(areas: List[OverseaArea]) extends ShipOverseaStatus

object ShipOverseaStatus {

  implicit val AreaCaseCode = OverseaAreaCaseCodes.ResponseCaseCode

  def parseAreasVld(str: String): Validation[ApiError, List[OverseaArea]] = {
    def error = JsonParseError("shipOverseasArea can't be parsed")
    if (str endsWith "/")
      error.failure
    else
      str
        .split('/')
        .toList
        .map(fromCaseCode[String, OverseaArea])
        .sequence[Option, OverseaArea]
        .toSuccess(error)
  }

  def parseVld(flag: Int, areas: String): Validation[ApiError, ShipOverseaStatus] = flag match {
    case 0 => DisableShipOversea.success
    case 1 => parseAreasVld(areas) ∘ AcceptShipOversea.apply
    case _ => JsonParseError("shipOverseasFlag must be in 0, 1").failure
  }
}
