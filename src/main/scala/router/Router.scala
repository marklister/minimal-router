package router

/* Stolen from Finagle*/

import monix.execution.Cancelable
import monix.reactive.Observable
import monix.reactive.observers.Subscriber
import monix.execution.Scheduler.Implicits.global
import org.scalajs.dom.raw.{HashChangeEvent, Window}
import org.scalajs.dom.window

import scala.concurrent.Future

/*
* Router was created before there was a documented prohibition against performing Outwatch.render multiple times
* It always worked fine but one should rather use the simpler ObsRouter in this file
*/

class Router[P, U](renderFunc: P => U)(pageSelector: Path => P) {

  def redirect(s: String) = window.location.hash = s

  def back() = window.history.back()

  def forward() = window.history.forward()

  private def handleHashChange(e: HashChangeEvent): Unit = {
    upd()
  }

  private def upd(): Unit = {
    val hash = window.location.hash
    val page = pageSelector(Path(hash))
    renderFunc(page)
  }

  window.onhashchange = handleHashChange(_)
  upd()
}

class ObsRouter[N](pageSelector: Path => Future[N])(implicit w: Window) extends Observable[N] {

  var subscribers = Seq.empty[Subscriber[N]]

  def redirect(s: String) = window.location.hash = s

  def back() = window.history.back()

  def forward() = window.history.forward()

  override def unsafeSubscribeFn(subscriber: Subscriber[N]): Cancelable = {
    subscribers = subscribers :+ subscriber
    upd.map(p => subscriber.onNext(p))
    () => subscribers = Seq.empty[Subscriber[N]]
  }

  private def upd() = {
    val hash = w.location.hash
    pageSelector(Path(hash))
  }

  w.onhashchange = handleHashChange(_)
  upd().map(p => subscribers.foreach(_.onNext(p))) //Todo handle backpressure

  private def handleHashChange(e: HashChangeEvent): Unit =
    upd.map { p =>
      subscribers.foreach(_.onNext(p))
    }
}

object ObsRouter {
  def apply[N](p: Path => Future[N])(implicit w: Window) = new ObsRouter(p)
}