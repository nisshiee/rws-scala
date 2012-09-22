package jp.co.rakuten.webservice

import org.scala_tools.time.Imports._
import scalaz._, Scalaz._

sealed trait AsurakuStatus
case object DisableAsuraku extends AsurakuStatus
case class AcceptAsuraku(closingTime: LocalTime, areas: List[AsurakuArea]) extends AsurakuStatus

object AsurakuStatus {

  val Time = """(\d{2}):(\d{2})""".r

  def parseClosingTimeVld(str: String): Validation[ApiError, LocalTime] = str match {
    case Time(h, m) => {
      val timeVld = for {
        h <- h.parseInt
        m <- m.parseInt
        time <- try { new LocalTime(h, m).success } catch { case e => e.failure }
      } yield time
      ~(~timeVld >| JsonParseError("asurakuClosingTime must be HH:MM"))
    }
    case _ => JsonParseError("asurakuClosingTime must be HH:MM").failure
  }

  def parseAreasVld(str: String): Validation[ApiError, List[AsurakuArea]] = {
    def error = JsonParseError("asurakuArea can't be parsed")
    if (str endsWith "/")
      error.failure
    else
      str
        .split('/')
        .toList
        .map(AsurakuArea.parseOpt)
        .sequence[Option, AsurakuArea]
        .toSuccess(error)
  }

  def parseVld(
    flag: Int, closingTimeStr: String, areaStr: String
  ): Validation[ApiError, AsurakuStatus] = flag match {
    case 0 => DisableAsuraku.success
    case 1 => for {
      closingTime <- parseClosingTimeVld(closingTimeStr)
      areas <- parseAreasVld(areaStr)
    } yield AcceptAsuraku(closingTime, areas)
    case _ => JsonParseError("asurakuFlag must be in 0, 1").failure
  }

}
