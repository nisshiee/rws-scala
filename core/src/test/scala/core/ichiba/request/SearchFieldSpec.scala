package rwsscala.ichiba

import org.specs2._

class SearchFieldSpec extends Specification { def is =

  "SearchField"                                                                                     ^
    "param"                                                                                         ^
      "Strictの場合Seq()が返る"                                                                     ! e1^
      "Broadの場合Seq(\"field\" -> \"0\")が返る"                                                    ! e2^
                                                                                                    end

  import SearchField._

  def e1 = Strict.param must beEmpty
  def e2 = Broad.param must equalTo(Seq("field" -> "0"))
}
