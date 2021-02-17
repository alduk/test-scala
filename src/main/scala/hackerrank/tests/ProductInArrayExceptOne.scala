package hackerrank.tests

object ProductInArrayExceptOne extends App{

  def product(arr: Array[Int]): Array[Int] = {
    val prod = arr.fold(1)(_ * _)
    arr.map(prod/_)
    /*val ret = Array.ofDim[Int](arr.length)
    for(i <- 0 until arr.length){
      ret(i) = prod / arr(i)
    }
    ret*/
  }

  //TODO Implement without division
  def product2(arr: Array[Int]): Array[Int] = {
    val prod = arr.fold(1)(_ * _)
    arr.map(prod/_)
    /*val ret = Array.ofDim[Int](arr.length)
    for(i <- 0 until arr.length){
      ret(i) = prod / arr(i)
    }
    ret*/
  }

  println(product(Array()).mkString("Array(", ", ", ")"))
  println(product(Array(1, 2, 3, 4, 5)).mkString("Array(", ", ", ")"))
  println(product(Array(3, 2, 1)).mkString("Array(", ", ", ")"))
}
