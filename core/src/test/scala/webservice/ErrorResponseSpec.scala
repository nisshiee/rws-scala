package jp.co.rakuten.webservice

import org.specs2._, matcher.DataTables

class ErrorResponseSpec extends Specification with DataTables { def is =

  "ErrorResponse"                                                               ^
    "parseOpt"                                                                  ^
      "JSONのSyntaxに従っていない場合はNone"                                    ! e1^
      "エラーレスポンス時のJSON構成でない場合はNone"                            ! e2^
      "正常なJSONを渡された場合、Some[ErrorResponse]が返る"                     ! e3^
                                                                                end

  def e1 =
    "json"               || "result" |
    """abcde"""          !! None     |
    """123abd"""         !! None     |
    """[abc"", "de"]"""  !! None     |
    """{ dummy: aaa }""" !! None     |> { (j, r) =>
      ErrorResponse.parseOpt(j) must equalTo(r)
    }

  def e2 =
    "json"                   || "result" |
    """"abcde""""            !! None     |
    """123"""                !! None     |
    """["abc", "de"]"""      !! None     |
    """{ "dummy": "aaa" }""" !! None     |> { (j, r) =>
      ErrorResponse.parseOpt(j) must equalTo(r)
    }

  val validExampleJson1 =
    """{"error": "error", "error_description": "description"}"""
  val validExampleResult1 = Some(ErrorResponse("error", "description"))

  val validExampleJson2 = """{
"error": "wrong_parameter",
"error_description": "page must be a number"
}"""
  val validExampleResult2 = Some(
    ErrorResponse(
       "wrong_parameter"
      ,"page must be a number"
    )
  )

  def e3 =
    "json"            || "result"            |
    validExampleJson1 !! validExampleResult1 |
    validExampleJson2 !! validExampleResult2 |> { (j, r) =>
      ErrorResponse.parseOpt(j) must equalTo(r)
    }
}
