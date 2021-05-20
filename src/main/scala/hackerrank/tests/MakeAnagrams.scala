package hackerrank.tests
/*
How many characters should one delete to make two given strings anagrams of each other?
https://www.hackerrank.com/challenges/making-anagrams/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
*/
object MakeAnagrams extends App {

    def solution(s1: String, s2: String) = {
      val arr = Array.ofDim[Int](256)
      for(e <- s1) arr(e) +=1
      for(e <- s2) arr(e) -=1
      //println(s1, s2, arr.mkString("Array(", ", ", ")"))
      arr.map(Math.abs).sum
    }


  println(solution("", ""))
  println(solution("a", ""))
  println(solution("", "a"))
  println(solution("a", "b"))
  println(solution("awe", "bwewqf"))
}
