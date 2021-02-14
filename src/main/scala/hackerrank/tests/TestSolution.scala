package hackerrank.tests

object TestSolution {

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
import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._



object Result1 {

  /*
   * Complete the 'fizzBuzz' function below.
   *
   * The function accepts INTEGER n as parameter.
   */

  def fizzBuzz(n: Int) {
    (1 to n) foreach{
      case x if x % 3 == 0 && x % 5 == 0 => println("FizzBuzz")
      case x if x % 3 == 0 => println("Fizz")
      case x if x % 5 == 0 => println("Buzz")
      case x => println(x)
    }
  }

}

object Solution {
  def main(args: Array[String]) {
    val n = StdIn.readLine.trim.toInt

    Result1.fizzBuzz(n)
  }
}
