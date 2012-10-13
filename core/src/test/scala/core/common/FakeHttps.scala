package rwsscala

import rwsscala.httptrait._
import scalaz._, Scalaz._

case class FakeHttps (
   expectedDomain: String
  ,expectedPath: String
  ,expectedParams: Map[String, String]
  ,validReturn: Validation[ConnectionError, Response]
  ,invalidReturn: Validation[ConnectionError, Response]
) extends RwsHttps {

  override def get (
     domain: String
    ,path: String
    ,params: Map[String, String]
  ): Validation[ConnectionError, Response] =
    if (domain == expectedDomain && path == expectedPath && params == expectedParams)
      validReturn
    else
      invalidReturn
}
