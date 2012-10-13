package rwsscala

import rwsscala.param.ichiba._
import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._
import org.scala_tools.time.Imports._

class ShipOverseaStatusSpec extends Specification with DataTables { def is =

  "ShipOverseaStatus"                                                                               ^
    "parseAreasVld"                                                                                 ^
      "「/」区切りの海外配送エリア文字列をparseし、結果をList[OverseaArea]のsuccessで返す"          ! e1^
      ("空文字の場合や、「/」による分割後に不正な文字列が1つでもある場合" +
         "、JsonParseErrorをfailureで返す")                                                         ! e2^
                                                                                                    p^
    "parseVld"                                                                                      ^
      "flag = 0の場合はDisableShipOverseaをsuccessで返す"                                           ! e3^
      "flag = 1で、areasのparseに成功した場合はAcceptShipOverseaをsuccessで返す"                    ! e4^
      "flag = 1で、areasのparseに失敗した場合はJsonParseErrorをfailureで返す"                       ! e5^
      "flagが0,1以外の場合はJsonParseErrorをfailureで返す"                                          ! e6^
                                                                                                    end


  def e1 =
    "input"                                   || "result"                              |
    "ワールドワイド"                          !! List(AllCountry)                      |
    "英国"                                    !! List(UnitedKingdom)                   |
    "アメリカ/アルゼンチン/ブラジル"          !! List(UnitedStates, Argentina, Brazil) |> { (input, result) =>
      ShipOverseaStatus.parseAreasVld(input) must equalTo(result.success)
    }

  def e2 =
    "input"                    || "result"  |
    ""                         !! "failure" |
    "/"                        !! "failure" |
    "abc"                      !! "failure" |
    "アメリカ/ダミー/ブラジル" !! "failure" |
    "アルゼンチン/"            !! "failure" |
    "/英国"                    !! "failure" |> { (input, result) =>
      ShipOverseaStatus.parseAreasVld(input).toEither must beLeft.like {
        case JsonParseError(_) => ok
      }
    }

  def e3 = ShipOverseaStatus.parseVld(0, "ワールドワイド") must equalTo(DisableShipOversea.success)

  def e4 =
    "areaStr"        || "result"                                         |
    "ワールドワイド" !! AcceptShipOversea(List(AllCountry))              |
    "中国/香港/台湾" !! AcceptShipOversea(List(China, HongKong, Taiwan)) |> { (areaStr, result) =>
        ShipOverseaStatus.parseVld(1, areaStr) must equalTo(result.success)
    }

  def e5 =
    "areaStr" || "result"  |
    ""        !! "failure" |
    "abc"     !! "failure" |> { (areaStr, result) =>
      ShipOverseaStatus.parseVld(1, areaStr).toEither must beLeft.like {
        case JsonParseError(_) => ok
      }
    }

  def e6 =
    "flag" | "areaStr"        |
    -1     ! "ワールドワイド" |
    2      ! "アメリカ/韓国"  |
    100    ! "dummy"          |
    999    ! "dummy"          |> { (flag, areaStr) =>
      ShipOverseaStatus.parseVld(flag, areaStr).toEither must beLeft.like {
        case JsonParseError(_) => ok
      }
    }
}
