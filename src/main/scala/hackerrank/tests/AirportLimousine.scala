package hackerrank.tests

object AiroportLimousine {

}

import java.io._
import java.math._
import java.security._
import java.text._
import java.util._
import java.util.concurrent._
import java.util.function._
import java.util.regex._
import java.util.stream._
import scala.annotation.tailrec
import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._


// TODO Check solution
object Result {

  /*
   * Complete the 'collectMax' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts 2D_INTEGER_ARRAY mat as parameter.
   */

  def collectMax(mat: Array[Array[Int]]): Int = {
    def go (m: Array[Array[Int]], x: Int, y:Int, riders: Int, deltaX: Int, deltaY: Int): Int = {
      if(x == mat.length -1 && y == mat.length - 1) {
        val delta = if(m(x)(y) == 1) 1 else 0
        println("back", x, y, m(x)(y), riders)
        if(m(x)(y) >= 0)
          Math.max(go(m, x - deltaX, y, riders + delta, -deltaX, -deltaY), go(m, x, y - deltaY, riders + delta, -deltaX, -deltaY))
        else
          0
      } else {
        if (x < 0 || y < 0 || x >= mat.length || y >= mat.length) {
          0
        } else {
          println(x, y, m(x)(y), riders)
          mat(x)(y) match {
            case 0 =>
              Math.max(go(m, x + deltaX, y, riders, deltaX, deltaY), go(m, x, y + deltaY, riders, deltaX, deltaY))
            case 1 =>
              mat(x)(y) = 0
              val newM = mat.map(_.map(identity))
              Math.max(go(newM, x + deltaX, y, riders + 1, deltaX, deltaY), go(newM, x, y + deltaY, riders + 1, deltaX, deltaY))
            case -1 =>
              0
          }
        }
      }
    }
    go(mat, 0, 0, 0, 1, 1)
  }

}

object AirportLimousine {
  def main(args: Array[String]) {

    val matRows = StdIn.readLine.trim.toInt
    val matColumns = StdIn.readLine.trim.toInt

    val mat = Array.ofDim[Int](matRows, matColumns)

    for (i <- 0 until matRows) {
      mat(i) = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    }

    val result = Result.collectMax(mat)

   println(result)

  }
}
