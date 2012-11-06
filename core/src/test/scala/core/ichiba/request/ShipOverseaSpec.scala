package rwsscala.ichiba

import org.specs2._, matcher.DataTables

import rwsscala.util._

class ShipOverseaSpec extends Specification with DataTables { def is =

  "ShipOversea"                                                                                     ^
    "param"                                                                                         ^
      "Allの場合Seq()が返る"                                                                        ! e1^
      "OnlyAccept(AllCountry)の場合Seq(\"shipOverseasFlag\" -> \"1\")が返る"                        ! e2^
      ("OnlyOversea(AllCountry以外)の場合" +
         "Seq(\"shopOverseasFlag\" -> \"1\", \"shipOverseasArea\" -> code)が返る")                  ! e3^
                                                                                                    p^
    "area2shipOversea"                                                                              ^
      "OverseaAreaからOnlyAccept(area)にimplicit conversionされる"                                  ! e4^
                                                                                                    end

  import OverseaArea._
  import ShipOversea._
  import ItemSearchParameters._

  def e1 = (All: ShipOversea).param must beEmpty
  def e2 = (OnlyAccept(AllCountry): ShipOversea).param must equalTo(Seq("shipOverseasFlag" -> "1"))
  def e3 =
    "area"       | "code" |
    UnitedStates ! "US"   |> { (area, code) =>
      (OnlyAccept(area): ShipOversea).param must equalTo(Seq("shipOverseasFlag" -> "1", "shipOverseasArea" -> code))
    }

  def e4 =
    "area"       | "shipOversea"            |
    AllCountry   ! OnlyAccept(AllCountry)   |
    UnitedStates ! OnlyAccept(UnitedStates) |> { (area, shipOversea) =>
      val conv: ShipOversea = area
      conv must equalTo(shipOversea)
    }

}
