package rwsscala.ichiba

import rwsscala._
import scalaz._, Scalaz._
import net.liftweb.json._

case class ItemSearchResultJson (
   count: Int
  ,page: Int
  ,first: Int
  ,last: Int
  ,hits: Int
  ,carrier: Int
  ,pageCount: Int
  ,items: List[ItemJson]
)

object ItemSearchResultJson {

  def parseVld(jsonStr: String): Validation[ApiError, ItemSearchResultJson] = {
    implicit val format = DefaultFormats
    for {
      ast <- parseOpt(jsonStr).toSuccess[ApiError](JsonParseError("json syntax error"))
      arranged = ast.transform(ItemJson.itemTransformation)
      jsonModel <- arranged
        .extractOpt[ItemSearchResultJson]
        .toSuccess[ApiError](JsonParseError("json structure error"))
    } yield jsonModel
  }
}
