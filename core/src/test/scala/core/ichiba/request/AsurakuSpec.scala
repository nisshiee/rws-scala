package rwsscala.ichiba

import rwsscala._
import org.specs2._, matcher.DataTables

class AsurakuSpec extends Specification with DataTables { def is =

  "Asuraku"                                                                                         ^
    "param"                                                                                         ^
      "Asuraku.Allの場合Seq()が返る"                                                                ! e1^
      "OnlyAccept(AllArea)の場合Seq(\"asurakuFlag\" -> \"1\")が返る"                                ! e2^
      "OnlyAccept(AllArea以外)の場合Seq(\"asurakuFlag\" -> \"1\", \"asurakuArea\" -> code)が返る"   ! e3^
                                                                                                    p^
    "area2asuraku"                                                                                  ^
      "AsurakuAreaからOnlyAccept(area)にimplicit conversionされる"                                  ! e4^
                                                                                                    end

  import Asuraku._
  import AsurakuArea._

  def e1 = All.param must beEmpty
  def e2 = OnlyAccept(AllArea).param must equalTo(Seq("asurakuFlag" -> "1"))
  def e3 =
    "area"      | "code" |
    AllHokkaido ! "100"  |
    Hokkaido    ! "1"    |
    AllKanto    ! "102"  |
    Tokyo       ! "13"   |> { (area, code) =>
      OnlyAccept(area).param must equalTo(Seq("asurakuFlag" -> "1", "asurakuArea" -> code))
    }

  def e4 =
    "area"      | "asuraku"               |
    AllArea     ! OnlyAccept(AllArea)     |
    AllHokkaido ! OnlyAccept(AllHokkaido) |
    Hokkaido    ! OnlyAccept(Hokkaido)    |
    AllKanto    ! OnlyAccept(AllKanto)    |
    Tokyo       ! OnlyAccept(Tokyo)       |> { (area, asuraku) =>
      val asu: Asuraku = area
      asu must equalTo(asuraku)
    }

}
