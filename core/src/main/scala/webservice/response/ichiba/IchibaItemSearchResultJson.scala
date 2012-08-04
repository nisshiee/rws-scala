package jp.co.rakuten.webservice

import scalaz._, Scalaz._

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

class ItemJson (
   val itemName: String
  ,val catchcopy: String
  ,val itemCode: String
  ,val itemPrice: String
  ,val itemCaption: String
  ,val itemUrl: String
  ,val availability: Int
  ,val taxFlag: Int
  ,val postageFlag: Int
  ,val creditCardFlag: Int
  ,val shopName: String
  ,val shopCode: String
  ,val shopUrl: String
  ,val genreId: String
  ,val imageFlag: Int
  ,val smallImageUrls: List[String]
  ,val mediumImageUrls: List[String]
  ,val affiliateUrl: String
  ,val shopOfTheYearFlag: Int
  ,val shipOverseasFlag: Int
  ,val shipOverseasArea: String
  ,val asurakuFlag: Int
  ,val asurakuClosingTime: String
  ,val asurakuArea: String
  ,val affiliateRate: String
  ,val startTime: String
  ,val endTime: String
  ,val reviewCount: Int
  ,val reviewAverage: String
  ,val pointRate: Int
  ,val pointRateStartTime: String
  ,val pointRateEndTime: String
  ,val giftFlag: Int
)

object ItemJson {

  import net.liftweb.json._

  lazy val imageUrlExtraction: PartialFunction[JValue, JValue] = {
    case JObject(JField("imageUrl", jValue) :: Nil) => jValue
  }

  lazy val itemExtraction: PartialFunction[JValue, JValue] = {
    case JObject(JField("Item", jValue) :: Nil) => jValue
  }

  lazy val itemsRename: PartialFunction[JValue, JValue] = {
    case JField("Items", ja: JArray) => JField("items", ja)
  }

  lazy val itemTransformation: PartialFunction[JValue, JValue] =
    imageUrlExtraction orElse itemExtraction orElse itemsRename
}

object IchibaItemSearchResultJson {

  import net.liftweb.json._

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
