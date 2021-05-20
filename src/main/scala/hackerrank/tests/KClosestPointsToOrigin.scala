package hackerrank.tests

//https://www.youtube.com/watch?v=eaYX0Ee0Kcg&list=PLBZBJbE_rGRVnpitdvpdY9952IsKMDuev&index=5
object KClosestPointsToOrigin extends App {

  def distance(a: (Int, Int), b: (Int, Int)): Int = {
    val xDiff = a._1 - b._1
    val yDiff = a._2 - b._2
    xDiff*xDiff + yDiff*yDiff
  }

  // ~ k max/min items in array
  //TODO
  // sorting quick, merge => n*log n
  // selection => n*k
  // max heap => k + (n-k)*log k
  def solve(points: Array[(Int, Int)], k: Int): Array[(Int, Int)] = {
    points.length match {
      case l if l <= k => points
      case 0 => Array()
      case 1 => points
      case _ => {
        points.sortBy(p => distance(p, (0, 0))).take(k)
      }
    }
  }

  println(solve(Array((-2, 4), (0, -2), (-1, 0), (3, -5), (-2, -3), (3, 2)), 2).mkString("Array(", ", ", ")"))
}
