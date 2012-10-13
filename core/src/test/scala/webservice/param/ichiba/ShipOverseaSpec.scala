package rwsscala.param.ichiba

import rwsscala.Implicits._
import org.specs2._, matcher.DataTables

class ShipOverseaSpec extends Specification with DataTables { def is =

  "ShipOversea"                                                                                     ^
    "param"                                                                                         ^
      "ShipOverseaAllの場合Seq()が返る"                                                             ! e1^
      "OnlyOversea(AllCountry)の場合Seq(\"shipOverseasFlag\" -> \"1\")が返る"                       ! e2^
      ("OnlyOversea(AllCountry以外)の場合" +
         "Seq(\"shopOverseasFlag\" -> \"1\", \"shipOverseasArea\" -> code)が返る")                  ! e3^
                                                                                                    p^
    "area2shipOversea"                                                                              ^
      "OverseaAreaからOnlyOversea(area)にimplicit conversionされる"                                 ! e4^
                                                                                                    end

  def e1 = ShipOverseaAll.param must beEmpty
  def e2 = OnlyOversea(AllCountry).param must equalTo(Seq("shipOverseasFlag" -> "1"))
  def e3 =
    "area"       | "code" |
    UnitedStates ! "US"   |> { (area, code) =>
      OnlyOversea(area).param must equalTo(Seq("shipOverseasFlag" -> "1", "shipOverseasArea" -> code))
    }

  def e4 =
    "area"       | "shipOversea"             |
    AllCountry   ! OnlyOversea(AllCountry)   |
    UnitedStates ! OnlyOversea(UnitedStates) |> { (area, shipOversea) =>
      val conv: ShipOversea = area
      conv must equalTo(shipOversea)
    }

}
