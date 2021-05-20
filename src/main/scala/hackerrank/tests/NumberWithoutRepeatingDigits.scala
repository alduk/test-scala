package hackerrank.tests

object NumberWithoutRepeatingDigits extends App {

  var count = 0
  var t = 0
  1000 to 9999 foreach{i =>
    val arr = i.toString.toCharArray
    t+=1
    arr.length match {
      case 1 => count +=1
      case 2 => if (arr(0) != arr(1)) count+=1 else println(s"skipping $i")
      case 3 => if (arr(0) != arr(1) && arr(1) != arr(2)) count += 1 else println(s"skipping $i")
      case 4 => if(arr(0) != arr(1) && arr(1) != arr(2) && arr(2) != arr(3)) count += 1 else println(s"skipping $i")
    }
    println(arr.mkString("Array(", ", ", ")"), i, count, t, t-count)
  }
  println(count)
}
