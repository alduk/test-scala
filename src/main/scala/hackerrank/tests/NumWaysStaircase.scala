package hackerrank.tests

/*
https://www.youtube.com/watch?v=5o-kdjv7FD0&list=PLBZBJbE_rGRVnpitdvpdY9952IsKMDuev&index=2
 */
object NumWaysStaircase {

  def numWaysOdNWithX(n: Int, steps: Array[Int]): Int = {
    if(n == 0 || n == 1){
      1
    } else {
      val nums = Array.ofDim[Int](n + 1)
      nums(0) = 1
      nums(1) = 1
      (2 to n) foreach { i =>
        var total = 0
        steps.foreach { x =>
          if (i - x >= 0)
            total += nums(i - x)
        }
        nums(i) = total
        total = 0
      }
      println(nums.mkString("nums(", ", ", ")"))
      nums(n)
    }
  }

  case class A(i: Int)
  class B(i: Int) extends A(i)

  def main(args: Array[String]): Unit = {
    println(numWaysOdNWithX(5, Array(1,2)))
    println(numWaysOdNWithX(5, Array(1,2,3)))
    println(numWaysOdNWithX(5, Array(1,3, 5)))
    println(numWaysOdNWithX(30, Array(1,3, 5)))
  }
}
