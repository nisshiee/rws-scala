package rwsscala.ichiba

import rwsscala._
import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._
import org.scala_tools.time.Imports._

class ItemSpec extends Specification with DataTables { def is =

  "Item"                                                                                            ^
    "strOpt"                                                                                        ^
      "空文字かnullの場合はNone、それ以外の場合はその入力文字列のSomeを返す"                        ! e1^
                                                                                                    p^
    "doubleVld"                                                                                     ^
      "Doubleにparseできる場合はDouble値のsuccessを返す"                                            ! e2^
      "Doubleにparseできない場合はparamNameをmsgに含むJsonParseErrorのfailureを返す"                ! e3^
                                                                                                    p^
    "longVld"                                                                                       ^
      "Longにparseできる場合はLong値のsuccessを返す"                                                ! e4^
      "Longにparseできない場合はparamNameをmsgに含むJsonParseErrorのfailureを返す"                  ! e5^
                                                                                                    p^
    "dateTimeOptVld"                                                                                ^
      "空文字の場合はNoneをsuccessで返す"                                                           ! e6^
      "yyyy-MM-dd hh:mm形式の有効な時刻をの場合、Some[LocalDateTime]をsuccessで返す"                ! e7^
      ("フォーマットが不正な場合や、日時として不正な値の場合は" +
        "paramNameをmsgに含むJsonParseErrorのfailureを返す")                                        ! e8^
                                                                                                    p^
    "tfVld"                                                                                         ^
      "flagが1の場合はoneMeans、0の場合は!oneMeansがsuccessで返る"                                  ! e9^
      "flagが0,1以外の場合はparamNameをmsgに含むJsonParseErrorのfailureを返す"                      ! e10^
                                                                                                    p^
    "parseVld"                                                                                      ^
      "各パラメータが正常であればItemをsuccessで返す"                                               ! e11^
      "不正なパラメータを含む場合、JsonParseErrorのfailureを返す"                                   ! (e12_1 and e12_2 and e12_3 and e12_4)^
                                                                                                    end

  import AsurakuArea._
  import OverseaArea._

  def e1 =
    "input "       || "result"       |
    ""             !! None           |
    (null: String) !! None           |
    "string"       !! Some("string") |> { (input, result) =>
      Item.strOpt(input) must equalTo(result)
    }

  def e2 =
    "input" || "result"      |
    "0.0"   !! 0.0.success   |
    "1.2"   !! 1.2.success   |
    "-2.4"  !! -2.4.success  |
    "+4.7"  !! 4.7.success   |
    "123"   !! 123.0.success |> { (input, result) =>
      Item.doubleVld(input, "test") must equalTo(result)
    }

  def e3 =
    "input"  || "result"  |
    ""       !! "failure" |
    "abc"    !! "failure" |
    "日本語" !! "failure" |
    "a12.3"  !! "failure" |
    "12.3a"  !! "failure" |> { (input, _) =>
      Item.doubleVld(input, "test").toEither must beLeft.like {
        case JsonParseError(msg) => msg must contain("test")
      }
    }

  def e4 =
    "input" || "result"     |
    "0"     !! 0L.success   |
    "12"    !! 12L.success  |
    "-24"   !! -24L.success |
    "123"   !! 123L.success |> { (input, result) =>
      Item.longVld(input, "test") must equalTo(result)
    }

  def e5 =
    "input"  || "result"  |
    ""       !! "failure" |
    "abc"    !! "failure" |
    "日本語" !! "failure" |
    "a123"   !! "failure" |
    "123a"   !! "failure" |> { (input, _) =>
      Item.longVld(input, "test").toEither must beLeft.like {
        case JsonParseError(msg) => msg must contain("test")
      }
    }

  def e6 = Item.dateTimeOptVld("", "test").toEither must beRight.like {
    case None => ok
  }

  def e7 =
    "input"            || "result"                               |
    "2012-09-30 00:00" !! new LocalDateTime(2012, 9, 30, 0, 0)   |
    "2012-09-30 12:11" !! new LocalDateTime(2012, 9, 30, 12, 11) |
    "2012-09-30 23:59" !! new LocalDateTime(2012, 9, 30, 23, 59) |
    "2012-02-29 18:45" !! new LocalDateTime(2012, 2, 29, 18, 45) |> { (input, result) =>
      Item.dateTimeOptVld(input, "test").toEither must beRight.like {
        case Some(d) => d must equalTo(result)
      }
    }

  def e8 =
    "input"             || "result"  |
    "2012-09-30 24:00"  !! "failure" |
    "2012-9-30 00:00"   !! "failure" |
    "a2012-09-30 12:11" !! "failure" |
    "2012-09-30 23:59a" !! "failure" |
    "日本語"            !! "failure" |
    "2011-02-29 18:45"  !! "failure" |> { (input, _) =>
      Item.dateTimeOptVld(input, "test").toEither must beLeft.like {
        case JsonParseError(msg) => msg must contain("test")
      }
    }

  def e9 =
    "flag" | "oneMeans" | "result" |
    1      ! true       ! true     |
    1      ! false      ! false    |
    0      ! true       ! false    |
    0      ! false      ! true     |> { (flag, oneMeans, result) =>
      Item.tfVld(flag, oneMeans, "test").toEither must beRight.like {
        case b => b must equalTo(result)
      }
    }

  def e10 =
    "flag" | "oneMeans" |
    -1     ! true       |
    2      ! false      |
    10     ! true       |
    -99    ! false      |> { (flag, oneMeans) =>
      Item.tfVld(flag, oneMeans, "test").toEither must beLeft.like {
        case JsonParseError(msg) => msg must contain("test")
      }
    }

  def e11 = {
    val itemJson = new ItemJson (
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
    Item.parseVld(itemJson).toEither must beRight.like { case i: Item =>
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
    }
  }

  def e12_1 = {
    val itemJson = new ItemJson (
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
    Item.parseVld(itemJson).toEither must beLeft.like {
      case JsonParseError(msg) => msg must contain("itemPrice")
    }
  }

  def e12_2 = {
    val itemJson = new ItemJson (
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
      ,2 // <- imageFlag must be in 0, 1
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
    Item.parseVld(itemJson).toEither must beLeft.like {
      case JsonParseError(msg) => msg must contain("imageFlag")
    }
  }

  def e12_3 = {
    val itemJson = new ItemJson (
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
      ,"hoge" // <- invalid shipOverseasArea
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
    Item.parseVld(itemJson).toEither must beLeft.like {
      case JsonParseError(msg) => msg must contain("shipOverseasArea")
    }
  }

  def e12_4 = {
    val itemJson = new ItemJson (
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
      ,"dummy" // <- asurakuClosingTime must be Time
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
    Item.parseVld(itemJson).toEither must beLeft.like {
      case JsonParseError(msg) => msg must contain("asurakuClosingTime")
    }
  }

}

