package jp.co.rakuten.webservice.util

import org.specs2._, matcher.DataTables

class TenthPlaceSpec extends Specification with DataTables { def is =

  "int2str"                                                                                         ^
    "Int値の1/10の値を小数第一位までの文字列として表現"                                             ! e1^
                                                                                                    p^
  "double2int"                                                                                      ^
    "Double値を小数第ニ位を四捨五入し、その十倍の値のInt値として表現"                               ! e2^
                                                                                                    p^
  "int2double"                                                                                      ^
    "Int値の1/10の値のDouble値として表現"                                                           ! e3^
                                                                                                    end

  def e1 =
    "int"  | "str"     |
    0      ! "0.0"     |
    1      ! "0.1"     |
    10     ! "1.0"     |
    13     ! "1.3"     |
    100    ! "10.0"    |
    12345  ! "1234.5"  |
    -1     ! "-0.1"    |
    -10    ! "-1.0"    |
    -13    ! "-1.3"    |
    -100   ! "-10.0"   |
    -12345 ! "-1234.5" |> { (int, str) =>
      TenthPlace.int2str(int) must equalTo(str)
    }

  def e2 =
    "double" | "int"  |
    0.0      ! 0      |
    -0.0     ! 0      |
    0.1      ! 1      |
    1.0      ! 10     |
    1.3      ! 13     |
    10.0     ! 100    |
    1234.5   ! 12345  |
    -0.1     ! -1     |
    -1.0     ! -10    |
    -1.3     ! -13    |
    -10.0    ! -100   |
    -1234.5  ! -12345 |
    0.04999  ! 0      |
    0.05     ! 1      |
    0.075    ! 1      |
    0.09999  ! 1      |
    0.10001  ! 1      |
    0.125    ! 1      |
    0.14999  ! 1      |
    0.15     ! 2      |
    -0.04999 ! -0     |
    -0.05    ! -1     |
    -0.075   ! -1     |
    -0.09999 ! -1     |
    -0.10001 ! -1     |
    -0.125   ! -1     |
    -0.14999 ! -1     |
    -0.15    ! -2     |> { (double, int) =>
      TenthPlace.double2int(double) must equalTo(int)
    }

  def e3 =
    "int"  | "double" |
    0      ! 0.0      |
    1      ! 0.1      |
    10     ! 1.0      |
    13     ! 1.3      |
    100    ! 10.0     |
    12345  ! 1234.5   |
    -1     ! -0.1     |
    -10    ! -1.0     |
    -13    ! -1.3     |
    -100   ! -10.0    |
    -12345 ! -1234.5  |> { (int, double) =>
      TenthPlace.int2double(int) must equalTo(double)
    }

}
