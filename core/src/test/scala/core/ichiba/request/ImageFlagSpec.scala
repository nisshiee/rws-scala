package rwsscala.ichiba

import org.specs2._

class ImageFlagSpec extends Specification { def is =

  "ImageFlag"                                                                                       ^
    "param"                                                                                         ^
      "Allの場合Seq()が返る"                                                                        ! e1^
      "OnlyHaveの場合Seq(\"imageFlag\" -> \"1\")が返る"                                             ! e2^
                                                                                                    end

  import ImageFlag._

  def e1 = All.param must beEmpty
  def e2 = OnlyHave.param must equalTo(Seq("imageFlag" -> "1"))
}
