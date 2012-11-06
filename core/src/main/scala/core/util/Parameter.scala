package rwsscala.util

trait Parameter[A] {
  def param: A => Seq[(String, String)]
}

trait ParameterOps[A] {
  def self: A
  def instance: Parameter[A]

  def param = instance.param(self)
}

trait Parameters {
  implicit def ToParameterOps[A: Parameter](a: A) = new ParameterOps[A] {
    def self: A = a
    def instance = implicitly[Parameter[A]]
  }
}
