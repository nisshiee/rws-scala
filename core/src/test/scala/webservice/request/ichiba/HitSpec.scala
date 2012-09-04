package jp.co.rakuten.webservice

import org.specs2._, matcher.DataTables

class HitSpec extends Specification with DataTables { def is =

  "Hit"                                                                                             ^
    "apply/unapply"                                                                                 ^
      "1-30の場合、その値をvalueに保持するHitオブジェクトが返る"                                    ! e1^
      "1未満の場合、1をvalueに保持するHitオブジェクトが返る"                                        ! e2^
      "31以上の場合、30をvalueに保持するHitオブジェクトが返る"                                      ! e3^
                                                                                                    p^
    "param"                                                                                         ^
      "30の場合Seq()が返る"                                                                         ! e4^
      "v != 30の場合Seq(\"hits\" -> \"$v\")が返る"                                                  ! e5^
                                                                                                    p^
    "int2hit"                                                                                       ^
      "IntからHitにapplyでimplicit conversionされる"                                                ! e6^
                                                                                                    end

  def e1 =
    "input" | "output" |
    1       ! 1        |
    15      ! 15       |
    30      ! 30       |> { (input, output) =>
      (Hit(input).value must equalTo(output)) and
      (Hit(input) match {
        case Hit(v) => v must equalTo(output)
        case _ => ko
      })
    }

  def e2 =
    "input" | "output" |
    -99     ! 1        |
    -1      ! 1        |
    0       ! 1        |> { (input, output) =>
      (Hit(input).value must equalTo(output)) and
      (Hit(input) match {
        case Hit(v) => v must equalTo(output)
        case _ => ko
      })
    }

  def e3 =
    "input" | "output" |
    31      ! 30       |
    99      ! 30       |> { (input, output) =>
      (Hit(input).value must equalTo(output)) and
      (Hit(input) match {
        case Hit(v) => v must equalTo(output)
        case _ => ko
      })
    }

  def e4 =
    "value" | "result" |
    30      ! Seq()    |
    31      ! Seq()    |> { (value, result) =>
      Hit(value).param must beEmpty
    }

  def e5 =
    "value" | "result"            |
    -1      ! Seq("hits" -> "1")  |
    0       ! Seq("hits" -> "1")  |
    1       ! Seq("hits" -> "1")  |
    15      ! Seq("hits" -> "15") |
    29      ! Seq("hits" -> "29") |> { (value, result) =>
      Hit(value).param must equalTo(result)
    }

  def e6 =
    "int" | "hit"   |
    -1    ! Hit(-1) |
    1     ! Hit(1)  |
    15    ! Hit(15) |
    30    ! Hit(30) |
    31    ! Hit(31) |> { (int, hit) =>
      val conv: Hit = int
      conv must equalTo(hit)
    }
}
