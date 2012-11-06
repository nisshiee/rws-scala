package rwsscala.ichiba

import rwsscala._
import rwsscala.util._
import scalaz._, Scalaz._

case class ItemSearchResult (
   count: Int
  ,page: Int
  ,first: Int
  ,last: Int
  ,hits: Int
  ,carrier: Carrier
  ,pageCount: Int
  ,items: List[Item]
)

object ItemSearchResult {

  def itemsVld(json: List[ItemJson]): Validation[ApiError, List[Item]] = {
    type Vld[A] = Validation[ApiError, A]
    (json map Item.parseVld).sequence[Vld, Item]
  }

  import ItemSearchCaseCodes._

  def parseVld(json: ItemSearchResultJson): Validation[ApiError, ItemSearchResult] = for {
    carrier <- fromCaseCode[Int, Carrier](json.carrier) toSuccess JsonParseError("carrier must be in 0, 1, 2")
    items <- itemsVld(json.items)
   } yield ItemSearchResult(
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

