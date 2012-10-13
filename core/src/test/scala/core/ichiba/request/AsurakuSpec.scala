package rwsscala.ichiba

import rwsscala._
import org.specs2._, matcher.DataTables

class AsurakuSpec extends Specification with DataTables { def is =

  "Asuraku"                                                                                         ^
    "param"                                                                                         ^
      "AsurakuAllの場合Seq()が返る"                                                                 ! e1^
      "OnlyAsuraku(AllArea)の場合Seq(\"asurakuFlag\" -> \"1\")が返る"                               ! e2^
      "OnlyAsuraku(AllArea以外)の場合Seq(\"asurakuFlag\" -> \"1\", \"asurakuArea\" -> code)が返る"  ! e3^
                                                                                                    p^
    "area2asuraku"                                                                                  ^
      "AsurakuAreaからOnlyAsuraku(area)にimplicit conversionされる"                                 ! e4^
                                                                                                    end

  def e1 = AsurakuAll.param must beEmpty
  def e2 = OnlyAsuraku(AllArea).param must equalTo(Seq("asurakuFlag" -> "1"))
  def e3 =
    "area"      | "code" |
    AllHokkaido ! "100"  |
    Hokkaido    ! "1"    |
    AllKanto    ! "102"  |
    Tokyo       ! "13"   |> { (area, code) =>
      OnlyAsuraku(area).param must equalTo(Seq("asurakuFlag" -> "1", "asurakuArea" -> code))
    }

  def e4 =
    "area"      | "asuraku"                |
    AllArea     ! OnlyAsuraku(AllArea)     |
    AllHokkaido ! OnlyAsuraku(AllHokkaido) |
    Hokkaido    ! OnlyAsuraku(Hokkaido)    |
    AllKanto    ! OnlyAsuraku(AllKanto)    |
    Tokyo       ! OnlyAsuraku(Tokyo)       |> { (area, asuraku) =>
      val asu: Asuraku = area
      asu must equalTo(asuraku)
    }

}
