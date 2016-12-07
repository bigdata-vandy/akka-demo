package edu.vanderbilt.accre.myhttpapp

import akka.actor.{ActorSystem, Props}
import edu.vanderbilt.accre.reaper.ProductionReaper
import edu.vanderbilt.accre.reaper.Reaper.WatchMe

object MyHttpApp extends App {
  val system = ActorSystem("HelloSystem")

  val uri = "http://export.arxiv.org/api/query" +
    "?search_query=all:electron&start=0&max_results=1"
  // "http://akka.io"

  val helloActor = system.actorOf(Props(new Requestor(uri)),
    name = "helloactor")

  val reaper = system.actorOf(Props[ProductionReaper])

  reaper ! WatchMe(helloActor)
}
