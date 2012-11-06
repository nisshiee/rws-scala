package rwsscala.ichiba

import org.specs2._, matcher.DataTables

import rwsscala.util._

class PurchaseTypeSpec extends Specification with DataTables { def is =

  "PurchaseType"                                                                                    ^
    "param"                                                                                         ^
      "NormalOrderの場合Seq()が返る"                                                                ! e1^
      "StandingOrderの場合Seq(\"purchaseType\" -> \"1\")が返る"                                     ! e2^
      "DistributeOrderの場合Seq(\"purchaseType\" -> \"2\")が返る"                                   ! e3^
                                                                                                    p^
    "CaseCode"                                                                                      ^
      "PurchaseType => Int"                                                                         ! e4^
      "Int => Some(PurchaseType)"                                                                   ! e5^
      "Int => None"                                                                                 ! e6^
                                                                                                    end

  import PurchaseType._
  import ItemSearchParameters._
  import ItemSearchCaseCodes._

  def e1 = (NormalOrder: PurchaseType).param must beEmpty
  def e2 = (StandingOrder: PurchaseType).param must equalTo(Seq("purchaseType" -> "1"))
  def e3 = (DistributeOrder: PurchaseType).param must equalTo(Seq("purchaseType" -> "2"))

  def e4 =
    "purchaseType"  | "int" |
    NormalOrder     ! 0     |
    StandingOrder   ! 1     |
    DistributeOrder ! 2     |> { (p: PurchaseType, i: Int) =>
      intCode(p) must equalTo(i)
    }

  def e5 =
    "int" | "purchaseType"  |
    0     ! NormalOrder     |
    1     ! StandingOrder   |
    2     ! DistributeOrder |> { (i: Int, p: PurchaseType) =>
      fromCaseCode[Int, PurchaseType](i) must beSome.which(p ==)
    }

  def e6 =
    "int" | "none" |
    -99   ! None   |
    -1    ! None   |
    3     ! None   |
    99    ! None   |> { (i: Int, n: Option[PurchaseType]) =>
      fromCaseCode[Int, PurchaseType](i) must beNone
    }
}
