enablePlugins(ScalaJSBundlerPlugin, ScalaJSPlugin)

name := "minimal-router"

version := "0.1.1-SNAPSHOT"

scalaVersion := "2.12.8"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.6",
  "io.monix" %% "monix" % "3.0.0-RC1",
  "org.scalatest" %%% "scalatest" % "3.0.7" % Test)

webpackConfigFile in fullOptJS := Some(baseDirectory.value / "prod.webpack.config.js")

webpackBundlingMode := BundlingMode.LibraryAndApplication()

//scalaJSUseMainModuleInitializer := true

scalaJSModuleKind := ModuleKind.CommonJSModule

//requireJsDomEnv in Test := true

useYarn := true

webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly()

emitSourceMaps:=true  