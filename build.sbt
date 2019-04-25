enablePlugins(ScalaJSPlugin)

organization := "com.github.marklister"

name := "minimal-router"

version := "0.1.1-SNAPSHOT"

scalaVersion := "2.12.8"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.6",
  "io.monix" %%% "monix" % "3.0.0-RC1",
  "io.github.outwatch" % "outwatch" % "master-SNAPSHOT" % Test,
  "org.scalatest" %%% "scalatest" % "3.0.7" % Test)

