package rwsscala.ichiba

import rwsscala._
import rwsscala.httptrait._
import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._

class ItemSearchSpec extends Specification with DataTables { def is =

  "ItemSearch"                                                                                      ^
    "apply"                                                                                         ^
      "RwsHttps実装がConnectionErrorを返した場合、ConnectionError.failureが返る"                    ! e1^
      "200で返ってもJSONからASTへの変換が不正の場合はJsonParseErrorがfailureで返る"                 ! e2^
      "200で返ってもASTからResultモデルへの変換が不正の場合はJsonParseErrorがfailureで返る"         ! e3^
      "200で返り、Resultモデルまで変換が成功した場合はItemSearchResultがsuccessで返る"              ! e4^
      "200,400,401,403,503,500以外が返された場合はUnknownResponseがfailureで返る"                   ! e5^
      "200以外でbodyが規定の形式でない場合はUnknownResponseがfailureで返る"                         ! e6^
      "400,401,403,503,500で、bodyの形式が正常の場合、codeに応じたBadResponseがsuccessで返る"       ! e7^
                                                                                                    end

  import Carrier._

  val appId = "1234567890"
  val basicParams = Map (
     "applicationId" -> appId
    ,"keyword" -> "test"
  )
  def basicApi(implicit https: RwsHttps): Validation[ApiError, ItemSearchResult] =
    ItemSearch(appId, ItemSearchBase | "test" |||)
  def basicTest[A] (
     responseCode: Int
    ,responseBody: String
  )(check: Validation[ApiError, ItemSearchResult] => A): A = {
    implicit val https = FakeIchibaItemSearchHttps (
       basicParams
      ,Response(responseCode, responseBody).success
      ,ConnectionError("invalid").failure
    )
    check(basicApi)
  }

  def e1 = {
    val expectedReturn = ConnectionError("valid").failure
    implicit val https = FakeIchibaItemSearchHttps (
       basicParams
      ,expectedReturn
      ,Response(200, "").success
    )
    basicApi must equalTo(expectedReturn)
  }

  def e2 = basicTest(200, "{ \"aaa\" }") {
    case Failure(JsonParseError(msg)) => msg must contain("syntax")
  }

  def e3 = basicTest(200, """{
  "count": 0,
  "page": 1,
  "first": 0,
  "last": 0,
  "hits": 0,
  "carrier": 0,
  "Items": []
}""") {
    case Failure(JsonParseError(msg)) => msg must not contain("syntax")
  }

  def e4 = basicTest(200, """{
  "count": 0,
  "page": 1,
  "first": 0,
  "last": 0,
  "hits": 0,
  "carrier": 0,
  "pageCount": 0,
  "Items": []
}""") {
    case Success (
      ItemSearchResult (0, 1, 0, 0, 0, PC, 0, Nil)
    ) => ok
    case _ => ko
  }

  def e5 = {
    val validError = """{"error": "wrong_parameter", "error_description": "page must be a number"}"""
    val invalidSyntaxError = """{ "error": """
    val invalidStructureError = """{"hoge": 123}"""

    "code" | "body"                |
    100    ! validError            |
    101    ! validError            |
    102    ! validError            |
    201    ! validError            |
    202    ! validError            |
    203    ! validError            |
    300    ! validError            |
    301    ! validError            |
    302    ! validError            |
    402    ! validError            |
    404    ! validError            |
    405    ! validError            |
    501    ! validError            |
    502    ! validError            |
    504    ! validError            |
    504    ! invalidSyntaxError    |
    504    ! invalidStructureError |> { (code, body) =>
      basicTest(code, body) {
        case Failure(UnknownResponse) => ok
      }
    }
  }

  def e6 = {
    val invalidSyntaxError = """{ "error": """
    val invalidStructureError = """{"hoge": 123}"""

    "code" | "body"                |
    100    ! invalidSyntaxError    |
    100    ! invalidStructureError |
    400    ! invalidSyntaxError    |
    401    ! invalidStructureError |
    403    ! invalidSyntaxError    |
    503    ! invalidStructureError |
    500    ! invalidStructureError |> { (code, body) =>
      basicTest(code, body) {
        case Failure(UnknownResponse) => ok
      }
    }
  }

  def e7 = {
    val validError = """{"error": "type", "error_description": "desc"}"""

    "code" | "error"                          |
    400    ! WrongParameter("type", "desc")   |
    401    ! InvalidToken("type", "desc")     |
    403    ! Forbidden("type", "desc")        |
    503    ! UnderMaintenance("type", "desc") |
    500    ! ApiSystemError("type", "desc")   |> { (code, error) =>
      basicTest(code, validError) {
        _ must equalTo(error.failure)
      }
    }
  }
}

object FakeIchibaItemSearchHttps {

  val defaultValidReturn = Response(200, """{
  "count": 0,
  "page": 1,
  "first": 0,
  "last": 0,
  "hits": 0,
  "carrier": 0,
  "pageCount": 0,
  "Items": []
}""").success[ConnectionError]

  val defaultInvalidReturn = ConnectionError("test failure").failure[Response]

  def apply (
     expectedParams: Map[String, String]
    ,validReturn: Validation[ConnectionError, Response] = defaultValidReturn
    ,invalidReturn: Validation[ConnectionError, Response] = defaultInvalidReturn
  ) = FakeHttps (
     "app.rakuten.co.jp"
    ,"/services/api/IchibaItem/Search/20120723"
    ,expectedParams
    ,validReturn
    ,invalidReturn
  )
}
