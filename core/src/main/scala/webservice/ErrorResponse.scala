package jp.co.rakuten.webservice

import net.liftweb.json._

case class ErrorResponse(error: String, description: String)

object ErrorResponse {

  implicit val format: Formats = DefaultFormats

  def parseOpt(json: String): Option[ErrorResponse] = for {
    ast <- JsonParser.parseOpt(json)
    arranged = ast.transform {
      case JField("error_description", js: JString) => JField("description", js)
    }
    model <- arranged.extractOpt[ErrorResponse]
  } yield model
}
