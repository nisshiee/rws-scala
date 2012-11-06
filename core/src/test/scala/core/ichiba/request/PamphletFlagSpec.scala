package rwsscala.ichiba

import org.specs2._

import rwsscala.util._

class PamphletFlagSpec extends Specification { def is =

  "PamphletFlag"                                                                                    ^
    "param"                                                                                         ^
      "Allの場合Seq()が返る"                                                                        ! e1^
      "OnlyAcceptの場合Seq(\"pamphletFlag\" -> \"1\")が返る"                                        ! e2^
                                                                                                    end

  import PamphletFlag._
  import ItemSearchParameters._

  def e1 = (All: PamphletFlag).param must beEmpty
  def e2 = (OnlyAccept: PamphletFlag).param must equalTo(Seq("pamphletFlag" -> "1"))
}
