package rwsscala.param.ichiba

import rwsscala.Implicits._
import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._

class MaxAffiliateRateSpec extends Specification with DataTables { def is =

  "MaxAffiliateRate"                                                                                ^
    "apply/unapply"                                                                                 ^
      ("小数第二位で四捨五入して1.0-99.9の範囲内の場合、" +
         "その値をvalueに保持するMaxAffiliateRateオブジェクトが返る")                               ! e1^
      ("小数第二位で四捨五入して1.0未満の場合、" +
         "1.0をvalueに保持するMaxAffiliateRateオブジェクトが返る")                                  ! e2^
      ("小数第二位で四捨五入して100.0以上の場合、" +
         "99.9をvalueに保持するMaxAffiliateRateオブジェクトが返る")                                 ! e3^
      "MaxAffiliateRate.OffをunapplyするとNoneが返る"                                               ! e4^
                                                                                                    p^
    "param"                                                                                         ^
      "MaxAffiliateRate.Offの場合Seq()が返る"                                                       ! e5^
      "MaxAffiliateRate.Onの場合Seq(\"maxAffiliateRate\" -> \"$value\")が返る"                      ! e6^
                                                                                                    p^
    "double2maxAffiliateRate"                                                                       ^
      "DoubleからMaxAffiliateRateにapplyでimplicit conversionされる"                                ! e7^
                                                                                                    p^
    "float2maxAffiliateRate"                                                                        ^
      "FloatからMaxAffiliateRateにapplyでimplicit conversionされる"                                 ! e8^
                                                                                                    p^
    "doubleOpt2maxAffiliateRate"                                                                    ^
      "Option[Double]からMaxAffiliateRateにapplyでimplicit conversionされる"                        ! e9^
                                                                                                    p^
    "floatOpt2maxAffiliateRate"                                                                     ^
      "Option[Int]からMaxAffiliateRateにapplyでimplicit conversionされる"                           ! e10^
                                                                                                    end

  def e1 =
    "input" | "output" |
    0.95    ! 1.0      |
    1.0     ! 1.0      |
    1.04999 ! 1.0      |
    23.6    ! 23.6     |
    45.3001 ! 45.3     |
    99.9499 ! 99.9     |> { (input, output) =>
      MaxAffiliateRate(input) match {
        case MaxAffiliateRate(actual) => actual must equalTo(output)
        case _ => ko
      }
    }

  def e2 =
    "input" | "output" |
    0.94999 ! 1.0      |
    0.9     ! 1.0      |
    0.0     ! 1.0      |
    -1.0    ! 1.0      |> { (input, output) =>
      MaxAffiliateRate(input) match {
        case MaxAffiliateRate(actual) => actual must equalTo(output)
        case _ => ko
      }
    }

  def e3 =
    "input" | "output" |
    99.95   ! 99.9     |
    100.0   ! 99.9     |
    999.9   ! 99.9     |> { (input, output) =>
      MaxAffiliateRate(input) match {
        case MaxAffiliateRate(actual) => actual must equalTo(output)
        case _ => ko
      }
    }

  def e4 = MaxAffiliateRate.Off match {
    case MaxAffiliateRate(_) => ko
    case _ => ok
  }

  def e5 = MaxAffiliateRate.Off.param must beEmpty


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
      MaxAffiliateRate(input).param must equalTo(Seq("maxAffiliateRate" -> output))
    }

  def e7 =
    "double" | "maxAffiliateRate"      |
    0.7      ! MaxAffiliateRate(0.7)   |
    1.0      ! MaxAffiliateRate(1.0)   |
    23.45    ! MaxAffiliateRate(23.45) |
    99.9     ! MaxAffiliateRate(99.9)  |
    100.2    ! MaxAffiliateRate(100.2) |> { (double, maxAffiliateRate) =>
      val conv: MaxAffiliateRate = double
      conv must equalTo(maxAffiliateRate)
    }

  def e8 =
    "float" | "maxAffiliateRate"       |
    0.7f    ! MaxAffiliateRate(0.7f)   |
    1.0f    ! MaxAffiliateRate(1.0f)   |
    23.45f  ! MaxAffiliateRate(23.45f) |
    99.9f   ! MaxAffiliateRate(99.9f)  |
    100.2f  ! MaxAffiliateRate(100.2f) |> { (float, maxAffiliateRate) =>
      val conv: MaxAffiliateRate = float
      conv must equalTo(maxAffiliateRate)
    }

  def e9 =
    "doubleOpt" | "maxAffiliateRate"      |
    None        ! MaxAffiliateRate.Off    |
    0.7.some    ! MaxAffiliateRate(0.7)   |
    1.0.some    ! MaxAffiliateRate(1.0)   |
    23.45.some  ! MaxAffiliateRate(23.45) |
    99.9.some   ! MaxAffiliateRate(99.9)  |
    100.2.some  ! MaxAffiliateRate(100.2) |> { (doubleOpt: Option[Double], maxAffiliateRate) =>
      val conv: MaxAffiliateRate = doubleOpt
      conv must equalTo(maxAffiliateRate)
    }

  def e10 =
    "floatOpt"   | "maxAffiliateRate"       |
    None         ! MaxAffiliateRate.Off     |
    0.7f.some    ! MaxAffiliateRate(0.7f)   |
    1.0f.some    ! MaxAffiliateRate(1.0f)   |
    23.45f.some  ! MaxAffiliateRate(23.45f) |
    99.9f.some   ! MaxAffiliateRate(99.9f)  |
    100.2f.some  ! MaxAffiliateRate(100.2f) |> { (floatOpt: Option[Float], maxAffiliateRate) =>
      val conv: MaxAffiliateRate = floatOpt
      conv must equalTo(maxAffiliateRate)
    }

}
