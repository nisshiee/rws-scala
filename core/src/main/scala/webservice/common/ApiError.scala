package jp.co.rakuten.webservice

import scalaz._, Scalaz._

trait ApiError {
  def mes: String
}
case class JsonParseError(mes: String) extends ApiError

trait BadResponse extends ApiError

case class WrongParameter(error: String, mes: String) extends BadResponse
case class InvalidToken(error: String, mes: String) extends BadResponse
case class Forbidden(error: String, mes: String) extends BadResponse
case class UnderMaintenance(error: String, mes: String) extends BadResponse
case class ApiSystemError(error: String, mes: String) extends BadResponse
case object UnknownResponse extends BadResponse {
  val mes = "unknown response"
}

object BadResponse {

  val code2Apply: Int => (String, String) => BadResponse = {
    case 400 => WrongParameter.apply _
    case 401 => InvalidToken.apply _
    case 403 => Forbidden.apply _
    case 503 => UnderMaintenance.apply _
    case 500 => ApiSystemError.apply _
    case _ => { (_, _) => UnknownResponse }
  }
}

trait ApiErrors {

  implicit val ApiErrorSemigroup = Semigroup.firstSemigroup[ApiError]
}
