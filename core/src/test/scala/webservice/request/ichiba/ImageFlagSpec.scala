package jp.co.rakuten.webservice

import org.specs2._

class ImageFlagSpec extends Specification { def is =

  "ImageFlag"                                                                                       ^
    "param"                                                                                         ^
      "ImageAllの場合Seq()が返る"                                                                   ! e1^
      "HasImageの場合Seq(\"imageFlag\" -> \"1\")が返る"                                             ! e2^
                                                                                                    end

  def e1 = ImageAll.param must beEmpty
  def e2 = HasImage.param must equalTo(Seq("imageFlag" -> "1"))
}
