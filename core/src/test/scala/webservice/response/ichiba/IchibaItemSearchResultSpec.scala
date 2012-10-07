package jp.co.rakuten.webservice

import jp.co.rakuten.webservice.param.ichiba._
import org.specs2._, matcher.MatchResult
import scalaz._, Scalaz._
import org.scala_tools.time.Imports._

class IchibaItemSearchResultSpec extends Specification { def is =

  "IchibaItemSearchResult"                                                                          ^
    "itemsVld"                                                                                      ^
      "空Listの場合は空Listをsuccessで返す"                                                         ! e1^
      "全て正常なItemJsonの場合、全てを変換したList[Item]をsuccessで返す"                           ! e2^
      "1つでも不正なItemJsonを含む場合、JsonParseErrorをfailureで返す"                              ! e3^
                                                                                                    p^
    "parseVld"                                                                                      ^
      "carrier, itemsのparseに成功した場合、parse結果をsuccessで返す"                               ! e4^
      "carrier, itemsいずれかのparseに失敗した場合、JsonParseErrorをfailureで返す"                  ! (e5_1 and e5_2)^
                                                                                                    end

  def e1 = IchibaItemSearchResult.itemsVld(List()) must equalTo(List().success)

  val validItemJson = new ItemJson (
     "itemName"
    ,"catchcopy"
    ,"itemCode"
    ,"1000"
    ,"itemCaption"
    ,"itemUrl"
    ,1
    ,0
    ,0
    ,1
    ,"shopName"
    ,"shopCode"
    ,"shopUrl"
    ,"123456"
    ,1
    ,List("small1", "small2")
    ,List("medium1", "medium2")
    ,"affiliateUrl"
    ,1
    ,1
    ,"ワールドワイド"
    ,1
    ,"12:00"
    ,"東京都/神奈川県"
    ,"5.5"
    ,"2012-10-01 00:00"
    ,"2012-10-01 23:59"
    ,123
    ,"4.23"
    ,10
    ,"2012-10-01 09:00"
    ,"2012-10-01 12:00"
    ,1
  )

  def validItemMatch(i: Item) =
    (i.name must equalTo("itemName")) and
    (i.catchcopy must equalTo("catchcopy")) and
    (i.code must equalTo("itemCode")) and
    (i.price must equalTo(1000L)) and
    (i.caption must equalTo("itemCaption")) and
    (i.url must equalTo("itemUrl")) and
    (i.affiliateUrl must equalTo("affiliateUrl".some)) and
    (i.imageStatus must equalTo(HasItemImage(List("small1", "small2"), List("medium1", "medium2")))) and
    (i.availability must beTrue) and
    (i.includeTax must beTrue) and
    (i.includePostage must beTrue) and
    (i.acceptCreditCard must beTrue) and
    (i.shopOfTheYear must beTrue) and
    (i.shipOverseaStatus must equalTo(AcceptShipOversea(List(AllCountry)))) and
    (i.asurakuStatus must equalTo(AcceptAsuraku(new LocalTime(12, 0), List(Tokyo, Kanagawa)))) and
    (i.affiliateRate must equalTo(5.5)) and
    (i.startTime must equalTo(new LocalDateTime(2012, 10, 1, 0, 0).some)) and
    (i.endTime must equalTo(new LocalDateTime(2012, 10, 1, 23, 59).some)) and
    (i.reviewCount must equalTo(123)) and
    (i.reviewAverage must equalTo(4.23)) and
    (i.pointRate must equalTo(10)) and
    (i.pointRateStartTime must equalTo(new LocalDateTime(2012, 10, 1, 9, 0).some)) and
    (i.pointRateEndTime must equalTo(new LocalDateTime(2012, 10, 1, 12, 0).some)) and
    (i.acceptGift must beTrue) and
    (i.shopName must equalTo("shopName")) and
    (i.shopCode must equalTo("shopCode")) and
    (i.shopUrl must equalTo("shopUrl")) and
    (i.genreId must equalTo(123456L))

  def e2 = {
    val input = List(validItemJson, validItemJson, validItemJson)
    IchibaItemSearchResult.itemsVld(input).toEither must beRight.like { case l: List[_] =>
      l.map(validItemMatch).foldLeft[MatchResult[Any]](ok)(_ and _)
    }
  }

  val invalidItemJson = new ItemJson (
     "itemName"
    ,"catchcopy"
    ,"itemCode"
    ,"abcde" // <- price must be Long
    ,"itemCaption"
    ,"itemUrl"
    ,1
    ,0
    ,0
    ,1
    ,"shopName"
    ,"shopCode"
    ,"shopUrl"
    ,"123456"
    ,1
    ,List("small1", "small2")
    ,List("medium1", "medium2")
    ,"affiliateUrl"
    ,1
    ,1
    ,"ワールドワイド"
    ,1
    ,"12:00"
    ,"東京都/神奈川県"
    ,"5.5"
    ,"2012-10-01 00:00"
    ,"2012-10-01 23:59"
    ,123
    ,"4.23"
    ,10
    ,"2012-10-01 09:00"
    ,"2012-10-01 12:00"
    ,1
  )

  def e3 = {
    val input = List(validItemJson, invalidItemJson, validItemJson)
    IchibaItemSearchResult.itemsVld(input).toEither must beLeft.like {
      case JsonParseError(msg) => msg must contain("itemPrice")
    }
  }

  def e4 = {
    val input = IchibaItemSearchResultJson(
       37
      ,1
      ,1
      ,30
      ,30
      ,0
      ,2
      ,List(validItemJson, validItemJson)
    )
    IchibaItemSearchResult.parseVld(input).toEither must beRight.like { case r: IchibaItemSearchResult =>
      (r.count must equalTo(37)) and
      (r.page must equalTo(1)) and
      (r.first must equalTo(1)) and
      (r.last must equalTo(30)) and
      (r.hits must equalTo(30)) and
      (r.carrier must equalTo(PC)) and
      (r.pageCount must equalTo(2)) and
      (r.items.map(validItemMatch).foldLeft[MatchResult[Any]](ok)(_ and _))
    }
  }

  def e5_1 = {
    val input = IchibaItemSearchResultJson(
       37
      ,1
      ,1
      ,30
      ,30
      ,-5
      ,2
      ,List(validItemJson, validItemJson)
    )
    IchibaItemSearchResult.parseVld(input).toEither must beLeft.like {
      case JsonParseError(msg) => msg must contain("carrier")
    }
  }

  def e5_2 = {
    val input = IchibaItemSearchResultJson(
       37
      ,1
      ,1
      ,30
      ,30
      ,0
      ,2
      ,List(invalidItemJson, validItemJson)
    )
    IchibaItemSearchResult.parseVld(input).toEither must beLeft.like {
      case JsonParseError(msg) => msg must contain("itemPrice")
    }
}

}

