package rwsscala.ichiba

import rwsscala._
import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._

class ItemSearchResultJsonSpec extends Specification with DataTables { def is =

  "ItemSearchResultJson"                                                                            ^
    "parseVld"                                                                                      ^
      "JSON Syntaxに従っていない場合はJsonParseErrorをfailureで返す"                                ! e1^
      "JSON構造が想定外の場合はJsonParseErrorをfailureで返す"                                       ! (e2_1 and e2_2 and e2_3 and e2_4)^
      "正しい構造のJSONの場合、parse結果のItemSearchResultJsonをsuccessで返す"                      ! e3^
                                                                                                    end

  def e1 =
    "input"              || "result"  |
    "123abc"             !! "faulure" |
    """{ "aaa", 123""" !! "faulure" |> { (input, _) =>
      ItemSearchResultJson.parseVld(input).toEither must beLeft.like {
        case JsonParseError(msg) => msg must contain("syntax")
      }
    }

  def e2_1 =
    "input"              || "result"  |
    """[1, 2, 3]"""      !! "faulure" |
    """{ "aaa", 123 }""" !! "faulure" |> { (input, _) =>
      ItemSearchResultJson.parseVld(input).toEither must beLeft.like {
        case JsonParseError(msg) => msg must contain("structure")
      }
    }

  def e2_2 = {
    val input = """{
  "count": 36,
  "page": 1,
  "first": 1,
  "last": 30,
  "hits": 30,
  "carrier": 0
}""" // "pageCount" が足りない
    ItemSearchResultJson.parseVld(input).toEither must beLeft.like {
      case JsonParseError(msg) => msg must contain("structure")
    }
  }

  def e2_3 = {
    val input = """{
  "count": "36",
  "page": 1,
  "first": 1,
  "last": 30,
  "hits": 30,
  "carrier": 0,
  "pageCount": 2
}""" // "count" の型がintでない
    ItemSearchResultJson.parseVld(input).toEither must beLeft.like {
      case JsonParseError(msg) => msg must contain("structure")
    }
  }

  def e2_4 = {
    val input = """{
  "count": 36,
  "page": 1,
  "first": 1,
  "last": 30,
  "hits": 30,
  "carrier": 0,
  "pageCount": 2,
  "Items": [
    {
      "Items": {
        "itemName": "商品名"
      }
    }
  ]
}""" // "Items" のメンバが足りない
    ItemSearchResultJson.parseVld(input).toEither must beLeft.like {
      case JsonParseError(msg) => msg must contain("structure")
    }
  }

  def e3 = {
    val input = """{
  "count": 36,
  "page": 1,
  "first": 1,
  "last": 30,
  "hits": 30,
  "carrier": 0,
  "pageCount": 2,
  "Items": [
    {
      "Item": {
        "itemName": "商品名",
        "catchcopy": "キャッチコピー",
        "itemCode": "test:10000000",
        "itemPrice": "1210",
        "itemCaption": "商品説明",
        "itemUrl": "http://item.rakuten.co.jp/test/10000000/",
        "affiliateUrl": "",
        "imageFlag": 1,
        "smallImageUrls": [
          {
            "imageUrl": "http://thumbnail.image.rakuten.co.jp/small"
          }
        ],
        "mediumImageUrls": [
          {
            "imageUrl": "http://thumbnail.image.rakuten.co.jp/medium"
          }
        ],
        "availability": 1,
        "taxFlag": 0,
        "postageFlag": 1,
        "creditCardFlag": 1,
        "shopOfTheYearFlag": 0,
        "shipOverseasFlag": 0,
        "shipOverseasArea": "",
        "asurakuFlag": 0,
        "asurakuClosingTime": "",
        "asurakuArea": "",
        "affiliateRate": "1.0",
        "startTime": "",
        "endTime": "",
        "reviewCount": 3,
        "reviewAverage": "4.67",
        "pointRate": 1,
        "pointRateStartTime": "",
        "pointRateEndTime": "",
        "giftFlag": 0,
        "shopName": "店舗名",
        "shopCode": "test",
        "shopUrl": "http://www.rakuten.co.jp/test/",
        "genreId": "111111"
      }
    }
  ]
}"""
    ItemSearchResultJson.parseVld(input).toEither must beRight.like {
      case ItemSearchResultJson(36, 1, 1, 30, 30, 0, 2, i :: Nil) =>
        (i.itemName must equalTo("商品名")) and
        (i.catchcopy must equalTo("キャッチコピー")) and
        (i.itemCode must equalTo("test:10000000")) and
        (i.itemPrice must equalTo("1210")) and
        (i.itemCaption must equalTo("商品説明")) and
        (i.itemUrl must equalTo("http://item.rakuten.co.jp/test/10000000/")) and
        (i.availability must equalTo(1)) and
        (i.taxFlag must equalTo(0)) and
        (i.postageFlag must equalTo(1)) and
        (i.creditCardFlag must equalTo(1)) and
        (i.shopName must equalTo("店舗名")) and
        (i.shopCode must equalTo("test")) and
        (i.shopUrl must equalTo("http://www.rakuten.co.jp/test/")) and
        (i.genreId must equalTo("111111")) and
        (i.imageFlag must equalTo(1)) and
        (i.smallImageUrls must equalTo(List("http://thumbnail.image.rakuten.co.jp/small"))) and
        (i.mediumImageUrls must equalTo(List("http://thumbnail.image.rakuten.co.jp/medium"))) and
        (i.affiliateUrl must equalTo("")) and
        (i.shopOfTheYearFlag must equalTo(0)) and
        (i.shipOverseasFlag must equalTo(0)) and
        (i.shipOverseasArea must equalTo("")) and
        (i.asurakuFlag must equalTo(0)) and
        (i.asurakuClosingTime must equalTo("")) and
        (i.asurakuArea must equalTo("")) and
        (i.affiliateRate must equalTo("1.0")) and
        (i.startTime must equalTo("")) and
        (i.endTime must equalTo("")) and
        (i.reviewCount must equalTo(3)) and
        (i.reviewAverage must equalTo("4.67")) and
        (i.pointRate must equalTo(1)) and
        (i.pointRateStartTime must equalTo("")) and
        (i.pointRateEndTime must equalTo("")) and
        (i.giftFlag must equalTo(0))
    }
  }
}

