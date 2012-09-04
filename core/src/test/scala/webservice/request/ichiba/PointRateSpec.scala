package jp.co.rakuten.webservice

import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._

class PointRateSpec extends Specification with DataTables { def is =

  "PointRate"                                                                                       ^
    "apply/unapply"                                                                                 ^
      "2-10の場合、その値をvalueに保持するPointRateオブジェクトが返る"                              ! e1^
      "2未満の場合、2をvalueに保持するPointRateオブジェクトが返る"                                  ! e2^
      "11以上の場合、10をvalueに保持するPointRateオブジェクトが返る"                                ! e3^
      "PointRate.OffをunapplyするとNoneが返る"                                                      ! e4^
      "PointRate.AnyRateをunapplyするとNoneが返る"                                                  ! e5^
                                                                                                    p^
    "param"                                                                                         ^
      "PointRate.Offの場合Seq()が返る"                                                              ! e6^
      "PointRate.AnyRateの場合Seq(\"pointRateFlag\" -> \"1\")が返る"                                ! e7^
      "PointRate.GivenRateの場合Seq(\"pointRateFlag\" -> \"1\", \"pointRate\" -> \"$value\")が返る" ! e8^
                                                                                                    p^
    "int2pointRate"                                                                                 ^
      "IntからPointRateにapplyでimplicit conversionされる"                                          ! e9^
                                                                                                    p^
    "intOpt2maxPrice"                                                                               ^
      "Option[Int]からPointRate.Onにapplyでimplicit conversionされる"                               ! e10^
                                                                                                    p^
                                                                                                    p^
  "PointRate.On"                                                                                    ^
    "apply/unapply"                                                                                 ^
      "2-10の場合、その値をvalueに保持するPointRateオブジェクトが返る"                              ! e11^
      "2未満の場合、2をvalueに保持するPointRateオブジェクトが返る"                                  ! e12^
      "11以上の場合、10をvalueに保持するPointRateオブジェクトが返る"                                ! e13^
      "PointRate.AnyRateをunapplyするとNoneが返る"                                                  ! e14^
                                                                                                    end

  def e1 =
    "input" | "output" |
    2       ! 2        |
    5       ! 5        |
    10      ! 10       |> { (input, output) =>
      PointRate(input) match {
        case PointRate.Off => ko
        case PointRate(actual) => actual must equalTo(output)
      }
    }

  def e2 =
    "input" | "output" |
    -1      ! 2        |
    0       ! 2        |
    1       ! 2        |> { (input, output) =>
      PointRate(input) match {
        case PointRate.Off => ko
        case PointRate(actual) => actual must equalTo(output)
      }
    }

  def e3 =
    "input" | "output" |
    11      ! 10       |
    99      ! 10       |> { (input, output) =>
      PointRate(input) match {
        case PointRate.Off => ko
        case PointRate(actual) => actual must equalTo(output)
      }
    }

  def e4 = PointRate.Off match {
    case PointRate(_) => ko
    case _ => ok
  }

  def e5 = PointRate.AnyRate match {
    case PointRate(_) => ko
    case _ => ok
  }

  def e6 = PointRate.Off.param must beEmpty

  def e7 = PointRate.AnyRate.param must equalTo(Seq("pointRateFlag" -> "1"))

  def e8 =
    "input" | "output" |
    1       ! "2"      |
    2       ! "2"      |
    5       ! "5"      |
    10      ! "10"     |
    11      ! "10"     |> { (input, output) =>
      PointRate(input).param must equalTo(Seq("pointRateFlag" -> "1", "pointRate" -> output))
    }

  def e9 =
    "int" | "pointRate"   |
    1     ! PointRate(1)  |
    2     ! PointRate(2)  |
    10    ! PointRate(10) |
    11    ! PointRate(11) |> { (int, pointRate) =>
      val conv: PointRate = int
      conv must equalTo(pointRate)
    }

  def e10 =
    "intOpt" | "pointRate"       |
    None     ! PointRate.AnyRate |
    1.some   ! PointRate(1)      |
    2.some   ! PointRate(2)      |
    10.some  ! PointRate(10)     |
    11.some  ! PointRate(10)     |> { (intOpt: Option[Int], pointRate) =>
      val conv: PointRate = intOpt
      conv must equalTo(pointRate)
    }

  def e11 =
    "input" | "output" |
    2       ! 2        |
    5       ! 5        |
    10      ! 10       |> { (input, output) =>
      PointRate.On(input) match {
        case PointRate.AnyRate => ko
        case PointRate.On(actual) => actual must equalTo(output)
      }
    }

  def e12 =
    "input" | "output" |
    -1      ! 2        |
    0       ! 2        |
    1       ! 2        |> { (input, output) =>
      PointRate.On(input) match {
        case PointRate.AnyRate => ko
        case PointRate.On(actual) => actual must equalTo(output)
      }
    }

  def e13 =
    "input" | "output" |
    11      ! 10       |
    99      ! 10       |> { (input, output) =>
      PointRate.On(input) match {
        case PointRate.AnyRate => ko
        case PointRate.On(actual) => actual must equalTo(output)
      }
    }

  def e14 = PointRate.AnyRate match {
    case PointRate.On(_) => ko
    case _ => ok
  }

}
