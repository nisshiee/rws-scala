package rwsscala.ichiba

import org.specs2._
import rwsscala.util._

class PostageFlagSpec extends Specification { def is =

  "PostageFlag"                                                                                     ^
    "param"                                                                                         ^
      "Allの場合Seq()が返る"                                                                        ! e1^
      "OnlyIncludeの場合Seq(\"postageFlag\" -> \"1\")が返る"                                        ! e2^
                                                                                                    end

  import PostageFlag._
  import ItemSearchParameters._

  def e1 = (All: PostageFlag).param must beEmpty
  def e2 = (OnlyInclude: PostageFlag).param must equalTo(Seq("postageFlag" -> "1"))
}
