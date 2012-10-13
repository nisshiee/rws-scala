package rwsscala.ichiba

import rwsscala._
import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._
import org.scala_tools.time.Imports._

class AsurakuStatusSpec extends Specification with DataTables { def is =

  "AsurakuStatsu"                                                                                   ^
    "parseClosingTimeVld"                                                                           ^
      """"HH:MM"の形式の文字列に対し、その時刻を表すLocalTimeをsuccessで返す"""                     ! e1^
      """"HH:MM"の形式でない場合、JsonParseErrorをfailureで返す"""                                  ! e2^
                                                                                                    p^
    "parseAreasVld"                                                                                 ^
      "「/」区切りのあす楽エリア文字列に対し、そのparse結果をList[AsurakuArea]のsuccessで返す"      ! e3^
      ("空文字の場合や、「/」による分割後に不正な文字列が1つでもある場合" +
         "、JsonParseErrorをfailureで返す")                                                         ! e4^
                                                                                                    p^
    "parseVld"                                                                                      ^
      "flag = 0の場合はDisableAsurakuをsuccessで返す"                                               ! e5^
      "flag = 1で、closingTime,areasのparseに成功した場合はAccesptAsurakuをsuccessで返す"           ! e6^
      "flag = 1で、closingTime,areasいずれかのparseに失敗した場合はJsonParseErrorをfailureで返す"   ! e7^
      "flagが0,1以外の場合はJsonParseErrorをfailureで返す"                                          ! e8^
                                                                                                    end

  import AsurakuArea._

  def e1 =
    "input" || "result"              |
    "00:00" !! new LocalTime(0, 0)   |
    "03:07" !! new LocalTime(3, 7)   |
    "11:23" !! new LocalTime(11, 23) |
    "18:09" !! new LocalTime(18, 9)  |
    "23:59" !! new LocalTime(23, 59) |> { (input, result) =>
      AsurakuStatus.parseClosingTimeVld(input) must equalTo(result.success)
    }

  def e2 =
    "input"  || "result"  |
    "abc"    !! "failure" |
    "日本語" !! "failure" |
    "1234"   !! "failure" |
    "1:234"  !! "failure" |
    "123:4"  !! "failure" |
    "24:00"  !! "failure" |> { (input, _) =>
      AsurakuStatus.parseClosingTimeVld(input).toEither must beLeft.like {
        case JsonParseError(_) => ok
      }
    }

  def e3 =
    "input"                                   || "result"                      |
    "全地域"                                  !! List(AllArea)                 |
    "北海道/青森県/岩手県"                    !! List(Hokkaido, Aomori, Iwate) |
    "関東エリアのすべて/甲信越エリアのすべて" !! List(AllKanto, AllKoshinetsu) |> { (input, result) =>
      AsurakuStatus.parseAreasVld(input) must equalTo(result.success)
    }

  def e4 =
    "input"                 || "result"  |
    ""                      !! "failure" |
    "/"                     !! "failure" |
    "abc"                   !! "failure" |
    "北海道/ダミー/岩手県"  !! "failure" |
    "北海道/"               !! "failure" |
    "/甲信越エリアのすべて" !! "failure" |> { (input, result) =>
      AsurakuStatus.parseAreasVld(input).toEither must beLeft.like {
        case JsonParseError(_) => ok
      }
    }

  def e5 =
    "closingTimeStr" || "areaStr" |
    "10:00"          !! "東京都"  |
    "dummy"          !! "東京都"  |
    "10:00"          !! "dummy"   |
    "dummy"          !! "dummy"   |> { (closingTimeStr, areaStr) =>
      AsurakuStatus.parseVld(0, closingTimeStr, areaStr).toEither must beRight.like {
        case DisableAsuraku => ok
      }
    }

  def e6 =
    "closingTimeStr" || "areaStr" | "result"                                                 |
    "10:00"          !! "東京都"  ! AcceptAsuraku(new LocalTime(10, 0), List(Tokyo)).success |> {
      (closingTimeStr, areaStr, result) =>
        AsurakuStatus.parseVld(1, closingTimeStr, areaStr) must equalTo(result)
    }

  def e7 =
    "closingTimeStr" || "areaStr" |
    "dummy"          !! "東京都"  |
    "10:00"          !! "dummy"   |
    "dummy"          !! "dummy"   |> { (closingTimeStr, areaStr) =>
        AsurakuStatus.parseVld(1, closingTimeStr, areaStr).toEither must beLeft.like {
          case JsonParseError(_) => ok
        }
    }

  def e8 =
    "flag" | "closingTimeStr" | "areaStr" |
    -1     ! "10:00"          ! "東京都"  |
    2      ! "dummy"          ! "東京都"  |
    100    ! "10:00"          ! "dummy"   |
    999    ! "dummy"          ! "dummy"   |> { (flag, closingTimeStr, areaStr) =>
        AsurakuStatus.parseVld(flag, closingTimeStr, areaStr).toEither must beLeft.like {
          case JsonParseError(_) => ok
        }
    }
}
