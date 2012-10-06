package jp.co.rakuten.webservice

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

