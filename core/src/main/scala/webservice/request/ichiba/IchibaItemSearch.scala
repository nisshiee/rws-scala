package jp.co.rakuten.webservice

import httptrait._
import util._
import scalaz._, Scalaz._

object IchibaItemSearch {

  def apply(
     applicationId: ApplicationId
    ,base: IchibaItemSearchBase
    ,affiliateId: AffiliateId = AffiliateId(None)
    ,shopCode: ShopCode = ShopCode.Off
    ,hit: Hit = 30
    ,page: Page = 1
    ,sort: Sort = Sort.Standard
    ,minPrice: MinPrice = MinPrice.Off
    ,maxPrice: MaxPrice = MaxPrice.Off
    ,availability: Availability = Available
    ,field: Field = StrictSearch
    ,carrier: Carrier = PC
    ,imageFlag: ImageFlag = ImageAll
    ,orFlag: OrFlag = AndSearch
    ,ngKeyword: NgKeyword = NgKeyword.Off
    ,purchaseType: PurchaseType = NormalOrder
    ,shipOversea: ShipOversea = ShipOverseaAll
    ,asuraku: Asuraku = AsurakuAll
    ,pointRate: PointRate = PointRate.Off
    ,postageFlag: PostageFlag = PostageAll
    ,creditCardFlag: CreditCardFlag = CreditCardAll
    ,giftFlag: GiftFlag = GiftAll
    ,hasReviewFlag: HasReviewFlag = HasReviewAll
    ,maxAffiliateRate: MaxAffiliateRate = MaxAffiliateRate.Off
    ,minAffiliateRate: MinAffiliateRate = MinAffiliateRate.Off
    ,hasMovieFlag: HasMovieFlag = HasMovieAll
    ,pamphletFlag: PamphletFlag = PamphletAll
    ,appointDeliveryDateFlag: AppointDeliveryDateFlag = AppointDeliveryDateAll
  )(
    implicit https: RwsHttps
  ): Validation[ApiError, IchibaItemSearchResult] = {
    val params = Seq(
       applicationId
      ,base
      ,affiliateId
      ,shopCode
      ,hit
      ,page
      ,sort
      ,minPrice
      ,maxPrice
      ,availability
      ,field
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
        jsonModel <- IchibaItemSearchResultJson.parseVld(jsonStr)
        result <- IchibaItemSearchResult.parseVld(jsonModel)
      } yield result
      case Response(code, jsonStr) => for {
        jsonModel <- ErrorResponse.parseOpt(jsonStr).toSuccess[ApiError](UnknownResponse)
        result <- BadResponse.code2Apply(code)(jsonModel.error, jsonModel.description).failure[IchibaItemSearchResult]
      } yield result
    }
  }
}
