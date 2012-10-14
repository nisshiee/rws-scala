package rwsscala.httptrait

import scalaz._, Scalaz._

trait RwsHttps {

  def get(domain: String, path: String, params: Map[String, String]): Validation[ConnectionError, Response]
}
