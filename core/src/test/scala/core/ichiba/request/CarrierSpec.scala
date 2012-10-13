package rwsscala.ichiba

import rwsscala._
import rwsscala.Implicits._
import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._

class CarrierSpec extends Specification with DataTables { def is =

  "Carrier"                                                                                         ^
    "param"                                                                                         ^
      "PCの場合Seq()が返る"                                                                         ! e1^
      "Mobileの場合Seq(\"carrier\" -> \"1\")が返る"                                                 ! e2^
      "SPの場合Seq(\"carrier\" -> \"2\")が返る"                                                     ! e3^
                                                                                                    p^
    "parseVld"                                                                                      ^
      "0,1,2の場合、対応するCarrierがsuccessで返る"                                                 ! e4^
      "0,1,2以外の場合、JsonParseErrorがfailureで返る"                                              ! e5^
                                                                                                    end

  def e1 = PC.param must beEmpty
  def e2 = Mobile.param must equalTo(Seq("carrier" -> "1"))
  def e3 = SP.param must equalTo(Seq("carrier" -> "2"))

  def e4 =
    "int" | "result"       |
    0     ! PC.success     |
    1     ! Mobile.success |
    2     ! SP.success     |> { (int, result) =>
      Carrier.parseVld(int) must equalTo(result)
    }

  def e5 =
    "int" | "result"         |
    -99   ! "JsonParseError" |
    -1    ! "JsonParseError" |
    3     ! "JsonParseError" |
    99    ! "JsonParseError" |> { (int, result) =>
      Carrier.parseVld(int) must beLike {
        case Failure(JsonParseError(_)) => ok
      }
    }
}
