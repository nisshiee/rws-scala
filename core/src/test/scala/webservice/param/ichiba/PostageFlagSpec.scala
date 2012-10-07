package jp.co.rakuten.webservice.param.ichiba

import org.specs2._

class PostageFlagSpec extends Specification { def is =

  "PostageFlag"                                                                                     ^
    "param"                                                                                         ^
      "PostageAllの場合Seq()が返る"                                                                 ! e1^
      "OnlyAllowPostageの場合Seq(\"postageFlag\" -> \"1\")が返る"                                   ! e2^
                                                                                                    end

  def e1 = PostageAll.param must beEmpty
  def e2 = OnlyFreePostage.param must equalTo(Seq("postageFlag" -> "1"))
}
