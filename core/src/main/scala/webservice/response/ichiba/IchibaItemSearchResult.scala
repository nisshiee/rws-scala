package jp.co.rakuten.webservice

import org.scala_tools.time.Imports._
import scalaz._, Scalaz._

case class IchibaItemSearchResult (
   count: Int
  ,page: Int
  ,first: Int
  ,last: Int
  ,hits: Int
  ,carrier: Carrier
  ,pageCount: Int
  ,items: List[Item]
)

class Item (
   val name: String
  ,val catchcopy: String
  ,val code: String
  ,val price: Long
  ,val caption: String
  ,val url: String
  ,val affiliateUrl: Option[String]
  ,val imageStatus: ItemImageStatus
  ,val availability: Boolean
  ,val includeTax: Boolean
  ,val includePostage: Boolean
  ,val acceptCreditCard: Boolean
  ,val shopOfTheYear: Boolean
  ,val asurakuStatus: AsurakuStatus
  ,val affiliateRate: Double
  ,val startTime: Option[LocalDateTime]
  ,val endTime: Option[LocalDateTime]
  ,val reviewCount: Int
  ,val reviewAverage: Double
  ,val pointRate: Int
  ,val pointRateStartTime: Option[LocalDateTime]
  ,val pointRateEndTime: Option[LocalDateTime]
  ,val accesptGift: Boolean
  ,val shopName: String
  ,val shopCode: String
  ,val shopUrl: String
  ,val genreId: Long
) {

  override def equals(o: Any): Boolean = o match {
    case i: Item => code == i.code
    case _ => false
  }

  override def hashCode: Int = code.hashCode
}

object IchibaItemSearchResult {

  def itemsVld(json: List[ItemJson]): Validation[ApiError, List[Item]] = {
    type Vld[A] = Validation[ApiError, A]
    (json map Item.parseVld).sequence[Vld, Item]
  }

  def parseVld(json: IchibaItemSearchResultJson): Validation[ApiError, IchibaItemSearchResult] = for {
    carrier <- Carrier.parseVld(json.carrier)
    items <- itemsVld(json.items)
   } yield IchibaItemSearchResult(
     json.count
    ,json.page
    ,json.first
    ,json.last
    ,json.hits
    ,carrier
    ,json.pageCount
    ,items
  )
}

object Item {

  val strOpt: String => Option[String] = {
    case "" => none
    case s => s.some
  }

  def doubleVld(str: String, paramName: String): Validation[ApiError, Double] =
    ~(~str.parseDouble >| JsonParseError(paramName |+| " must be Double"))

  def longVld(str: String, paramName: String): Validation[ApiError, Long] =
    ~(~str.parseLong >| JsonParseError(paramName |+| " must be Long"))

  val DateTime = """(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2})""".r

  def dateTimeOptVld(
    dateTimeStr: String, paramName: String
  ): Validation[ApiError, Option[LocalDateTime]] = dateTimeStr match {
    case "" => none.success
    case DateTime(ys, ms, ds, hs, mms) => {
      val dateTimeVld = for {
        y <- ys.parseInt
        m <- ms.parseInt
        d <- ds.parseInt
        h <- hs.parseInt
        mm <- mms.parseInt
        dateTime <- try { new LocalDateTime(y, m, d, h, mm).some.success } catch { case e => e.failure }
      } yield dateTime
      ~(~dateTimeVld >| JsonParseError(paramName |+| " must be DateTime"))
    }
    case _ => JsonParseError(paramName |+| " must be DateTime").failure
  }

  def tfVld(flag: Int, oneMeans: Boolean, paramName: String): Validation[ApiError, Boolean] =
    (flag, oneMeans) match {
      case (1, true) => true.success
      case (0, true) => false.success
      case (1, false) => false.success
      case (0, false) => true.success
      case _ => JsonParseError(paramName |+| " must be in 0, 1").failure
    }

  def parseVld(json: ItemJson): Validation[ApiError, Item] = for {
    price <- longVld(json.itemPrice, "itemPrice")
    imageStatus <- ItemImageStatus.parseVld(json.imageFlag, json.smallImageUrls, json.mediumImageUrls)
    availability <- tfVld(json.availability, true, "availability")
    includeTax <- tfVld(json.taxFlag, false, "taxFlag")
    includePostage <- tfVld(json.postageFlag, false, "postageFlag")
    acceptCreditCard <- tfVld(json.creditCardFlag, true, "creditCardFlag")
    shopOfTheYear <- tfVld(json.shopOfTheYearFlag, true, "shopOfTheYear")
    asuraku <- AsurakuStatus.parseVld(json.asurakuFlag, json.asurakuClosingTime, json.asurakuArea)
    affiliateRate <- doubleVld(json.affiliateRate, "affiliateRate")
    startTime <- dateTimeOptVld(json.startTime, "startTime")
    endTime <- dateTimeOptVld(json.endTime, "endTime")
    reviewAverage <- doubleVld(json.reviewAverage, "reviewAverage")
    pointRateStartTime <- dateTimeOptVld(json.pointRateStartTime, "pointRateStartTime")
    pointRateEndTime <- dateTimeOptVld(json.pointRateEndTime, "pointRateEndTime")
    giftFlag <- tfVld(json.giftFlag, true, "giftFlag")
    genreId <- longVld(json.genreId, "genreId")
  } yield new Item(
     json.itemName
    ,json.catchcopy
    ,json.itemCode
    ,price
    ,json.itemCaption
    ,json.itemUrl
    ,strOpt(json.affiliateUrl)
    ,imageStatus
    ,availability
    ,includeTax
    ,includePostage
    ,acceptCreditCard
    ,shopOfTheYear
    ,asuraku
    ,affiliateRate
    ,startTime
    ,endTime
    ,json.reviewCount
    ,reviewAverage
    ,json.pointRate
    ,pointRateStartTime
    ,pointRateEndTime
    ,giftFlag
    ,json.shopName
    ,json.shopCode
    ,json.shopUrl
    ,genreId
  )
}

