package hackerrank.tests

/*
2. Turnstile
A university has exactly one turnstile. It can be used either as an exit or an entrance. Unfortunately, sometimes many people want to pass through the turnstile and their directions can be different. The ith person comes to the turnstile at time[i] and wants to either exit the university if direction[i] = 1 or enter the university if direction[i] = 0.   People form 2 queues, one to exit and one to enter. They are ordered by the time when they came to the turnstile and, if the times are equal, by their indices.

If some person wants to enter the university and another person wants to leave the university at the same moment, there are three cases:

If in the previous second the turnstile was not used (maybe it was used before, but not at the previous second), then the person who wants to leave goes first.
If in the previous second the turnstile was used as an exit, then the person who wants to leave goes first.
If in the previous second the turnstile was used as an entrance, then the person who wants to enter goes first.
Passing through the turnstile takes 1 second.

For each person, find the time when they will pass through the turnstile.

Function Description

Complete the function getTimes in the editor below.

getTimes has the following parameters:

    int time[n]:  an array of n integers where the value at index i is the time in seconds when the ith person will come to the turnstile

    int direction[n]:  an array of n integers where the value at index i is the direction of the ith person

Returns:

    int[n]: an array of n integers where the value at index i is the time when the ith person will pass the turnstile

Constraints

1 ≤ n ≤ 105
0 ≤ time[i] ≤ 109  for 0 ≤ i ≤ n - 1
time[i] ≤ time[i + 1]  for 0 ≤ i ≤ n - 2
0 ≤ direction[i] ≤ 1  for 0 ≤ i ≤ n - 1

 */

object UniversityTurnstile {

  def arrange(time: Array[Int], direction: Array[Int]): Array[Int] = {
    require(time.length == direction.length && time.length > 0)
    println(time.mkString("Array(", ", ", ")"))
    println(direction.mkString("Array(", ", ", ")"))
    val timeM = time.zipWithIndex.map(v => (v._2, v._1)).toMap
    println(timeM)
    println(time.mkString("Array(", ", ", ")"))
    println(time.zipWithIndex.mkString("Array(", ", ", ")"))
    val (entry, exit) = time.zipWithIndex.partition(v => direction(v._2) == 0)
    println(entry.mkString("Array(", ", ", ")"))
    println(exit.mkString("Array(", ", ", ")"))
    val ret = Array.ofDim[Int](time.length)
    val wasUsed = false
    (time.min to time.max + 1).foreach{i =>

    }
    ret
  }

  def main(args: Array[String]): Unit = {
    val r = scala.util.Random
    val time = (1 to 10).map(_ => r.nextInt(50)).toArray
    val direction = (1 to 10 ).map(_ => r.nextInt(2)).toArray
    println(arrange(time, direction).mkString("Array(", ", ", ")"))
  }
}
