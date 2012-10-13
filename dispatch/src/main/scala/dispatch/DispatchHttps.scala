package rwsscala.dispatch

import rwsscala.httptrait._
import dispatch._
import com.ning.http.client.{ Response => DRes }
import scalaz._, Scalaz._

object DispatchHttps extends RwsHttps {

  object AsResponse extends (DRes => Response) {
    def apply(r: DRes) = Response(r.getStatusCode, r.getResponseBody)
  }

  override def get(
     domain: String
    ,path: String
    ,params: Map[String, String]
  ): Validation[ConnectionError, Response] =
    Http(:/(domain).secure / path <<? params > AsResponse).either.left.map {
      case e: Throwable => ConnectionError(e.getMessage)
    }() |> (_.disjunction.validation)
}
