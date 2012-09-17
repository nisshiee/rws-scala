package jp.co.rakuten.webservice

import org.specs2._

class PamphletFlagSpec extends Specification { def is =

  "PamphletFlag"                                                                                    ^
    "param"                                                                                         ^
      "PamphletAllの場合Seq()が返る"                                                                ! e1^
      "OnlyAllowPamphletの場合Seq(\"pamphletFlag\" -> \"1\")が返る"                                 ! e2^
                                                                                                    end

  def e1 = PamphletAll.param must beEmpty
  def e2 = OnlyAllowPamphlet.param must equalTo(Seq("pamphletFlag" -> "1"))
}
