package jp.co.rakuten.webservice

import org.specs2._, matcher.DataTables

class ApiErrorSpec extends Specification with DataTables { def is =

  "BadResponse"                                                                 ^
    "code2Apply"                                                                ^
      "レスポンスコードに対応するBadResponseのapplyを取得できる"                ! e1^
      "仕様外のレスポンスコードの場合、UnknownResponseを返すapplyを取得できる"  ! e2^
                                                                                end

  def e1 =
    "code" | "error" | "description" | "result"                       |
    400    ! "wp"    ! "desc"        ! WrongParameter("wp", "desc")   |
    401    ! "wp"    ! "desc"        ! InvalidToken("wp", "desc")     |
    403    ! "wp"    ! "desc"        ! Forbidden("wp", "desc")        |
    503    ! "wp"    ! "desc"        ! UnderMaintenance("wp", "desc") |
    500    ! "wp"    ! "desc"        ! ApiSystemError("wp", "desc")   |> { (c, e, d, r) =>
      BadResponse.code2Apply(c)(e, d) must equalTo(r)
    }

  def e2 =
    "code" | "error" | "description" | "result"        |
    100    ! "hoge"  ! "huga"        ! UnknownResponse |
    402    ! "hoge"  ! "huga"        ! UnknownResponse |
    404    ! "hoge"  ! "huga"        ! UnknownResponse |
    501    ! "hoge"  ! "huga"        ! UnknownResponse |
    502    ! "hoge"  ! "huga"        ! UnknownResponse |
    504    ! "hoge"  ! "huga"        ! UnknownResponse |> { (c, e, d, r) =>
      BadResponse.code2Apply(c)(e, d) must equalTo(r)
    }
}
  