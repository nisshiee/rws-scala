package rwsscala.ichiba

import rwsscala._
import rwsscala.httptrait._
import rwsscala.util._
import scalaz._, Scalaz._

object ItemSearch {

  def apply(
     base: ItemSearchBase
    ,shopCode: ShopCode = ShopCode.Off
    ,hit: Hit = 30
    ,page: Page = 1
    ,sort: Sort = Sort.Standard
    ,minPrice: MinPrice = MinPrice.Off
    ,maxPrice: MaxPrice = MaxPrice.Off
    ,availability: Availability = Availability.OnlyAvailable
    ,searchField: SearchField = SearchField.Strict
    ,carrier: Carrier = Carrier.PC
    ,imageFlag: ImageFlag = ImageFlag.All
    ,orFlag: OrFlag = OrFlag.And
    ,ngKeyword: NgKeyword = NgKeyword.Off
    ,purchaseType: PurchaseType = PurchaseType.NormalOrder
    ,shipOversea: ShipOversea = ShipOversea.All
    ,asuraku: Asuraku = Asuraku.All
    ,pointRate: PointRate = PointRate.Off
    ,postageFlag: PostageFlag = PostageFlag.All
    ,creditCardFlag: CreditCardFlag = CreditCardFlag.All
    ,giftFlag: GiftFlag = GiftFlag.All
    ,hasReviewFlag: HasReviewFlag = HasReviewFlag.All
    ,maxAffiliateRate: MaxAffiliateRate = MaxAffiliateRate.Off
    ,minAffiliateRate: MinAffiliateRate = MinAffiliateRate.Off
    ,hasMovieFlag: HasMovieFlag = HasMovieFlag.All
    ,pamphletFlag: PamphletFlag = PamphletFlag.All
    ,appointDeliveryDateFlag: AppointDeliveryDateFlag = AppointDeliveryDateFlag.All
  )(
    implicit
     applicationDetail: ApplicationDetail
    ,https: RwsHttps
  ): Validation[ApiError, ItemSearchResult] = {
    val params = Seq(
       applicationDetail.applicationId
      ,base
      ,applicationDetail.affiliateId
      ,shopCode
      ,hit
      ,page
      ,sort
      ,minPrice
      ,maxPrice
      ,availability
      ,searchField
      ,carrier
      ,imageFlag
      ,orFlag
      ,ngKeyword
      ,purchaseType
      ,shipOversea
      ,asuraku
      ,pointRate
      ,postageFlag
      ,creditCardFlag
      ,giftFlag
      ,hasReviewFlag
      ,maxAffiliateRate
      ,minAffiliateRate
      ,hasMovieFlag
      ,pamphletFlag
      ,appointDeliveryDateFlag
    ).map {
      p: Parameter => p.param
    }.flatten.toMap
    val res = https.get("app.rakuten.co.jp", "/services/api/IchibaItem/Search/20120723", params)
    res flatMap {
      case Response(200, jsonStr) => for {
        jsonModel <- ItemSearchResultJson.parseVld(jsonStr)
        result <- ItemSearchResult.parseVld(jsonModel)
      } yield result
      case Response(code, jsonStr) => for {
        jsonModel <- ErrorResponse.parseOpt(jsonStr).toSuccess[ApiError](UnknownResponse)
        result <- BadResponse.code2Apply(code)(jsonModel.error, jsonModel.description).failure[ItemSearchResult]
      } yield result
    }
  }
}
