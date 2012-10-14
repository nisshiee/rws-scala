package rwsscala.util

import scala.math._
import scalaz._, Scalaz._

/**
 * "小数第一位までの数値"をInt型として表現し、浮動小数点の誤差から解放するためのヘルパ
 */
object TenthPlace {

  def int2str: Int => String = { i =>
    val s = if (i < 0) "-" else ""
    val u = abs(i / 10)
    val d = abs(i % 10)
    s |+| u.toString |+| "." |+| d.toString
  }

  def int2double: Int => Double = { _.toDouble / 10.0 }

  def double2int: Double => Int = { d =>
    val s = if (d < 0.0) -1 else 1
    (abs(d) * 10.0 + 0.5).toInt * s
  }
}
