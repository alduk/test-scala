package akka.actors

import akka.Done
import akka.actor.ActorSystem
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.duration.{FiniteDuration, MILLISECONDS}
import scala.concurrent.{Future, Promise}
import scala.util.Random

object StreamsTest extends App{
  implicit val system: ActorSystem = ActorSystem("QuickStart")
  implicit val ec = system.dispatcher

  def spin(value: Int): Int = {
    val start = System.currentTimeMillis()
    while ((System.currentTimeMillis() - start) < 10) {}
    value
  }

  def test1(): Future[Done] = {
    Source(1 to 1000)
      .map(spin)
      .map(spin)
      .runWith(Sink.ignore)
  }

  def test2(): Future[Done] = {
    Source(1 to 1000)
      .map(spin)
      .async
      .map(spin)
      .runWith(Sink.ignore)
  }

  def test3(): Future[Done] = {
    Source(1 to 1000)
      .mapAsync(1)(x => Future(spin(x)))
      .mapAsync(1)(x => Future(spin(x)))
      .runWith(Sink.ignore)
  }

  def test4(): Future[Done] = {
    Source(1 to 1000)
      .mapAsync(4)(x => Future(spin(x)))
      .mapAsync(4)(x => Future(spin(x)))
      .runWith(Sink.ignore)
  }

  // Simulate a non-uniform CPU-bound workload
  def uniformRandomSpin(value: Int): Future[Int] = Future {
    val max = Random.nextInt(11)
    val start = System.currentTimeMillis()
    while ((System.currentTimeMillis() - start) < max) {}
    value
  }

  def test5(): Future[Done] = {
    Source(1 to 1000)
      .mapAsync(1)(uniformRandomSpin)
      .mapAsync(1)(uniformRandomSpin)
      .mapAsync(1)(uniformRandomSpin)
      .mapAsync(1)(uniformRandomSpin)
      .runWith(Sink.ignore)
  }

  def test6(): Future[Done] = {
    Source(1 to 1000)
      .mapAsync(1)(uniformRandomSpin).async
      .mapAsync(1)(uniformRandomSpin).async
      .mapAsync(1)(uniformRandomSpin).async
      .mapAsync(1)(uniformRandomSpin).async
      .runWith(Sink.ignore)
  }

  // Simulate a non-blocking network call to another service
  def nonBlockingCall(value: Int): Future[Int] = {
    val promise = Promise[Int]

    val max = FiniteDuration(Random.nextInt(101), MILLISECONDS)
    system.scheduler.scheduleOnce(max) {
      promise.success(value)
    }

    promise.future
  }

  def test7(): Future[Done] = {
    Source(1 to 1000)
      .mapAsync(1000)(nonBlockingCall)
      .runWith(Sink.ignore)
  }

  def test8(): Future[Done] = {
    Source(1 to 1000)
      .mapAsyncUnordered(8)(uniformRandomSpin)
      .runWith(Sink.ignore)
  }

  val start = System.currentTimeMillis()
  test8().onComplete(_ => system.terminate().foreach(_ => println(s"time: ${System.currentTimeMillis()-start}")))
}
