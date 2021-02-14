package thread.sleep

import java.util.concurrent.Executors

object ThreadSleep extends App {
  def task(id: Int): Runnable = () => {
    println(s"${Thread.currentThread().getName()} start-$id")
    Thread.sleep(10000)
    println(s"${Thread.currentThread().getName()} end-$id")
  }

  val oneThreadExecutor = Executors.newFixedThreadPool(2)

  // send 2 tasks to the executor
  (1 to 2).foreach(id =>
    oneThreadExecutor.execute(task(id)))}
