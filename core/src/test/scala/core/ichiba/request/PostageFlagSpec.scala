package rwsscala.ichiba

import org.specs2._

class PostageFlagSpec extends Specification { def is =

  "PostageFlag"                                                                                     ^
    "param"                                                                                         ^
      "Allの場合Seq()が返る"                                                                        ! e1^
      "OnlyIncludeの場合Seq(\"postageFlag\" -> \"1\")が返る"                                        ! e2^
                                                                                                    end

  import PostageFlag._

  def e1 = All.param must beEmpty
  def e2 = OnlyInclude.param must equalTo(Seq("postageFlag" -> "1"))
}
