package rwsscala.ichiba

import org.specs2._

class HasMovieFlagSpec extends Specification { def is =

  "HasMovieFlag"                                                                                    ^
    "param"                                                                                         ^
      "HasMovieAllの場合Seq()が返る"                                                                ! e1^
      "OnlyHasMovieの場合Seq(\"hasMovieFlag\" -> \"1\")が返る"                                      ! e2^
                                                                                                    end

  def e1 = HasMovieAll.param must beEmpty
  def e2 = OnlyHasMovie.param must equalTo(Seq("hasMovieFlag" -> "1"))
}
