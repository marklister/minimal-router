import java.util.{Observable, Observer}

import monix.execution.Ack.Continue
import monix.execution.{Ack, Scheduler}
import monix.reactive.observers.Subscriber
import outwatch.dom.dsl._
import org.scalatest.Matchers
import outwatch.dom.VNode
import router.{ObsRouter, Root}

import scala.concurrent.Future

class ObsRouterSpec extends Matchers {
  import monix.execution.Scheduler.Implicits.global

  val Page1="#page1"
  val Page2 = "#page2"

  val firstPage= Future(div(p("First Page")))
  val secondPage = Future(div(p("Second Page")))

  val r1= ObsRouter {path=>
    path match {
      case Root => firstPage
      case Root / Page1 => firstPage
      case Root / Page2 => secondPage
    }
  }
  val subscriber = new Subscriber[VNode] {
    var last= Seq.empty[VNode]
    override implicit def scheduler: Scheduler = monix.execution.Scheduler.Implicits.global

    override def onNext(elem: VNode): Future[Ack] = {
      last = last :+elem
      Continue
    }

    override def onError(ex: Throwable): Unit = {}

    override def onComplete(): Unit = {}
  }


}
