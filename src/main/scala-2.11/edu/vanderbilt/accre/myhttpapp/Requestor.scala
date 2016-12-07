package edu.vanderbilt.accre.myhttpapp

import akka.actor.{Actor, ActorLogging}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.ActorMaterializerSettings
import akka.util.ByteString

class Requestor(uri: String) extends Actor with ActorLogging {

  import akka.pattern.pipe
  import context.dispatcher

  final implicit val materializer: ActorMaterializer =
    ActorMaterializer(ActorMaterializerSettings(context.system))

  val http = Http(context.system)

  override def preStart() = {
    println("preStart")
    log.info("Requestor::preStart")
    http.singleRequest(HttpRequest(uri = uri))
      .pipeTo(self)
  }

  def receive = {
    case HttpResponse(StatusCodes.OK, headers, entity, _) =>
      println("foo")
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        log.info("Got response, body: " + body.utf8String)
      }
      log.info("Committing suicide")
      context.stop(self)
    case resp @ HttpResponse(code, _, _, _) =>
      log.info("Request failed, response code: " + code)
      resp.discardEntityBytes()
    case _ =>
      println("foo")
      context.stop(self)
  }

}
