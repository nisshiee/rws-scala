package rwsscala.ichiba

import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._
import ItemSearchBase._

class ItemSearchBaseSpec extends Specification with DataTables { def is =

  "ItemSearchBase"                                                                                  ^
    "Start"                                                                                         ^
      "-> SomeK"                                                                                    ! e1^
      "-> SomeKG"                                                                                   ! e2^
      "-> SomeKGI"                                                                                  ! e3^
                                                                                                    p^
    "SomeK"                                                                                         ^
      "-> SomeKG"                                                                                   ! e4^
      "-> SomeKGI"                                                                                  ! e5^
      "-> Base"                                                                                     ! e6^
                                                                                                    p^
    "NoneK"                                                                                         ^
      "-> SomeKG"                                                                                   ! e7^
      "-> SomeKGI"                                                                                  ! e8^
                                                                                                    p^
    "SomeKG"                                                                                        ^
      "-> SomeKGI"                                                                                  ! e9^
      "-> Base"                                                                                     ! e10^
                                                                                                    p^
    "NoneKG"                                                                                        ^
      "-> SomeKGI"                                                                                  ! e11^
                                                                                                    p^
    "SomeKGI"                                                                                       ^
      "-> Base"                                                                                     ! e12^
                                                                                                    p^
    "param"                                                                                         ! e13^
                                                                                                    end

  def e1 = (ItemSearchBase | "test") |> { s: SomeK =>
    s.keyword must equalTo("test")
  }
  def e2 = (ItemSearchBase || 123456) |> { s: SomeKG =>
    (s.keyword must beNone) and
    (s.genreId must beSome.which(123456L ==))
  }
  def e3 = (ItemSearchBase ||| "test") |> { s: SomeKGI =>
    (s.keyword must beNone) and
    (s.genreId must beNone) and
    (s.itemCode must beSome.which("test" ==))
  }

  def e4 = (ItemSearchBase | "test" | 123456) |> { s: SomeKG =>
    (s.keyword must beSome.which("test" ==)) and
    (s.genreId must beSome.which(123456L ==))
  }
  def e5 = (ItemSearchBase | "test" || "test2") |> { s: SomeKGI =>
    (s.keyword must beSome.which("test" ==)) and
    (s.genreId must beNone) and
    (s.itemCode must beSome.which("test2" ==))
  }
  def e6 = (ItemSearchBase | "test" |||) |> { s: ItemSearchBase =>
    (s.keyword must beSome.which("test" ==)) and
    (s.genreId must beNone) and
    (s.itemCode must beNone)
  }

  def e7 = (NoneK | 123456) |> { s: SomeKG =>
    (s.keyword must beNone) and
    (s.genreId must beSome.which(123456L ==))
  }
  def e8 = (NoneK || "test2") |> { s: SomeKGI =>
    (s.keyword must beNone) and
    (s.genreId must beNone) and
    (s.itemCode must beSome.which("test2" ==))
  }

  def e9 = (ItemSearchBase || 123456 | "test") |> { s: SomeKGI =>
    (s.keyword must beNone) and
    (s.genreId must beSome.which(123456L ==)) and
    (s.itemCode must beSome.which("test" ==))
  }
  def e10 = (ItemSearchBase || 123456 ||) |> { s: ItemSearchBase =>
    (s.keyword must beNone) and
    (s.genreId must beSome.which(123456L ==)) and
    (s.itemCode must beNone)
  }

  def e11 = (NoneKG | "test") |> { s: SomeKGI =>
    (s.keyword must beNone) and
    (s.genreId must beNone) and
    (s.itemCode must beSome.which("test" ==))
  }

  def e12 = (ItemSearchBase ||| "test" |) |> { s: ItemSearchBase =>
    (s.keyword must beNone) and
    (s.genreId must beNone) and
    (s.itemCode must beSome.which("test" ==))
  }

  def e13 =
    "base"                                                | "result"                                                                |
    (ItemSearchBase | "test1" | 123456 | "test2" |) ! Seq("keyword" -> "test1", "genreId" -> "123456", "itemCode" -> "test2") |
    (ItemSearchBase | "test1" | 123456 ||)          ! Seq("keyword" -> "test1", "genreId" -> "123456")                        |
    (ItemSearchBase | "test1" || "test2" |)         ! Seq("keyword" -> "test1", "itemCode" -> "test2")                        |
    (ItemSearchBase || 123456 | "test2" |)          ! Seq("genreId" -> "123456", "itemCode" -> "test2")                       |
    (ItemSearchBase | "test1" |||)                  ! Seq("keyword" -> "test1")                                               |
    (ItemSearchBase || 123456 ||)                   ! Seq("genreId" -> "123456")                                              |
    (ItemSearchBase ||| "test2" |)                  ! Seq("itemCode" -> "test2")                                              |> {
      (base, result) => base.param must equalTo(result)
    }
}
