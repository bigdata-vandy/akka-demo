import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

val fs = (1 to 100).map { i =>
  Future { Thread.sleep(i); i}
}

val f = Future.sequence(fs)

Await.result(f, Duration.Inf)