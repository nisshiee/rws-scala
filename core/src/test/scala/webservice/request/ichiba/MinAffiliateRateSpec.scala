package jp.co.rakuten.webservice

import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._

class MinAffiliateRateSpec extends Specification with DataTables { def is =

  "MinAffiliateRate"                                                                                ^
    "apply/unapply"                                                                                 ^
      ("小数第二位で四捨五入して1.0-99.9の範囲内の場合、" +
         "その値をvalueに保持するMinAffiliateRateオブジェクトが返る")                               ! e1^
      ("小数第二位で四捨五入して1.0未満の場合、" +
         "1.0をvalueに保持するMinAffiliateRateオブジェクトが返る")                                  ! e2^
      ("小数第二位で四捨五入して100.0以上の場合、" +
         "99.9をvalueに保持するMinAffiliateRateオブジェクトが返る")                                 ! e3^
      "MinAffiliateRate.OffをunapplyするとNoneが返る"                                               ! e4^
                                                                                                    p^
    "param"                                                                                         ^
      "MinAffiliateRate.Offの場合Seq()が返る"                                                       ! e5^
      "MinAffiliateRate.Onの場合Seq(\"minAffiliateRate\" -> \"$value\")が返る"                      ! e6^
                                                                                                    p^
    "double2minAffiliateRate"                                                                       ^
      "DoubleからMinAffiliateRateにapplyでimplicit conversionされる"                                ! e7^
                                                                                                    p^
    "float2minAffiliateRate"                                                                        ^
      "FloatからMinAffiliateRateにapplyでimplicit conversionされる"                                 ! e8^
                                                                                                    p^
    "doubleOpt2minAffiliateRate"                                                                    ^
      "Option[Double]からMinAffiliateRateにapplyでimplicit conversionされる"                        ! e9^
                                                                                                    p^
    "floatOpt2minAffiliateRate"                                                                     ^
      "Option[Int]からMinAffiliateRateにapplyでimplicit conversionされる"                           ! e10^
                                                                                                    end

  def e1 =
    "input" | "output" |
    0.95    ! 1.0      |
    1.0     ! 1.0      |
    1.04999 ! 1.0      |
    23.6    ! 23.6     |
    45.3001 ! 45.3     |
    99.9499 ! 99.9     |> { (input, output) =>
      MinAffiliateRate(input) match {
        case MinAffiliateRate(actual) => actual must equalTo(output)
        case _ => ko
      }
    }

  def e2 =
    "input" | "output" |
    0.94999 ! 1.0      |
    0.9     ! 1.0      |
    0.0     ! 1.0      |
    -1.0    ! 1.0      |> { (input, output) =>
      MinAffiliateRate(input) match {
        case MinAffiliateRate(actual) => actual must equalTo(output)
        case _ => ko
      }
    }

  def e3 =
    "input" | "output" |
    99.95   ! 99.9     |
    100.0   ! 99.9     |
    999.9   ! 99.9     |> { (input, output) =>
      MinAffiliateRate(input) match {
        case MinAffiliateRate(actual) => actual must equalTo(output)
        case _ => ko
      }
    }

  def e4 = MinAffiliateRate.Off match {
    case MinAffiliateRate(_) => ko
    case _ => ok
  }

  def e5 = MinAffiliateRate.Off.param must beEmpty


  def e6 =
    "input" | "output" |
    0.94999 ! "1.0"    |
    0.95    ! "1.0"    |
    1.0     ! "1.0"    |
    1.04999 ! "1.0"    |
    23.6    ! "23.6"   |
    45.3001 ! "45.3"   |
    99.9499 ! "99.9"   |
    99.95   ! "99.9"   |> { (input, output) =>
      MinAffiliateRate(input).param must equalTo(Seq("minAffiliateRate" -> output))
    }

  def e7 =
    "double" | "minAffiliateRate"      |
    0.7      ! MinAffiliateRate(0.7)   |
    1.0      ! MinAffiliateRate(1.0)   |
    23.45    ! MinAffiliateRate(23.45) |
    99.9     ! MinAffiliateRate(99.9)  |
    100.2    ! MinAffiliateRate(100.2) |> { (double, minAffiliateRate) =>
      val conv: MinAffiliateRate = double
      conv must equalTo(minAffiliateRate)
    }

  def e8 =
    "float" | "minAffiliateRate"       |
    0.7f    ! MinAffiliateRate(0.7f)   |
    1.0f    ! MinAffiliateRate(1.0f)   |
    23.45f  ! MinAffiliateRate(23.45f) |
    99.9f   ! MinAffiliateRate(99.9f)  |
    100.2f  ! MinAffiliateRate(100.2f) |> { (float, minAffiliateRate) =>
      val conv: MinAffiliateRate = float
      conv must equalTo(minAffiliateRate)
    }

  def e9 =
    "doubleOpt" | "minAffiliateRate"      |
    None        ! MinAffiliateRate.Off    |
    0.7.some    ! MinAffiliateRate(0.7)   |
    1.0.some    ! MinAffiliateRate(1.0)   |
    23.45.some  ! MinAffiliateRate(23.45) |
    99.9.some   ! MinAffiliateRate(99.9)  |
    100.2.some  ! MinAffiliateRate(100.2) |> { (doubleOpt: Option[Double], minAffiliateRate) =>
      val conv: MinAffiliateRate = doubleOpt
      conv must equalTo(minAffiliateRate)
    }

  def e10 =
    "floatOpt"   | "minAffiliateRate"       |
    None         ! MinAffiliateRate.Off     |
    0.7f.some    ! MinAffiliateRate(0.7f)   |
    1.0f.some    ! MinAffiliateRate(1.0f)   |
    23.45f.some  ! MinAffiliateRate(23.45f) |
    99.9f.some   ! MinAffiliateRate(99.9f)  |
    100.2f.some  ! MinAffiliateRate(100.2f) |> { (floatOpt: Option[Float], minAffiliateRate) =>
      val conv: MinAffiliateRate = floatOpt
      conv must equalTo(minAffiliateRate)
    }

}
