package rwsscala.ichiba

import scalaz._, Scalaz._
import net.liftweb.json._

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

  lazy val imageUrlExtraction: PartialFunction[JValue, JValue] = {
    case JObject(JField("imageUrl", (jstr: JString)) :: Nil) => jstr
  }

  lazy val itemExtraction: PartialFunction[JValue, JValue] = {
    case JObject(JField("Item", (jo: JObject)) :: Nil) => jo
  }

  lazy val itemsRename: PartialFunction[JValue, JValue] = {
    case JField("Items", ja: JArray) => JField("items", ja)
  }

  lazy val itemTransformation: PartialFunction[JValue, JValue] =
    imageUrlExtraction orElse itemExtraction orElse itemsRename
}

