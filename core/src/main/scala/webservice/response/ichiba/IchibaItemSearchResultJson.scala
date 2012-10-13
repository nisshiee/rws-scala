package rwsscala

import scalaz._, Scalaz._
import net.liftweb.json._

case class IchibaItemSearchResultJson (
   count: Int
  ,page: Int
  ,first: Int
  ,last: Int
  ,hits: Int
  ,carrier: Int
  ,pageCount: Int
  ,items: List[ItemJson]
)

object IchibaItemSearchResultJson {

  def parseVld(jsonStr: String): Validation[ApiError, IchibaItemSearchResultJson] = {
    implicit val format = DefaultFormats
    for {
      ast <- parseOpt(jsonStr).toSuccess[ApiError](JsonParseError("json syntax error"))
      arranged = ast.transform(ItemJson.itemTransformation)
      jsonModel <- arranged
        .extractOpt[IchibaItemSearchResultJson]
        .toSuccess[ApiError](JsonParseError("json structure error"))
    } yield jsonModel
  }
}
