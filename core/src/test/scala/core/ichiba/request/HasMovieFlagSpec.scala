package rwsscala.ichiba

import org.specs2._

class HasMovieFlagSpec extends Specification { def is =

  "HasMovieFlag"                                                                                    ^
    "param"                                                                                         ^
      "Allの場合Seq()が返る"                                                                        ! e1^
      "OnlyHaveの場合Seq(\"hasMovieFlag\" -> \"1\")が返る"                                          ! e2^
                                                                                                    end

  import HasMovieFlag._

  def e1 = All.param must beEmpty
  def e2 = OnlyHave.param must equalTo(Seq("hasMovieFlag" -> "1"))
}
