name := "akka-demo"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.0",
  "com.typesafe.akka" %% "akka-http-xml" % "10.0.0",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.14" % "test",
  "org.scalatest" %% "scalatest" % "latest.integration" % "test"
)