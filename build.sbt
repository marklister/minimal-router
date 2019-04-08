enablePlugins(ScalaJSBundlerPlugin)

name := "minimal-router"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.8"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.6",
  "com.github.outwatch" % "outwatch" % "master-SNAPSHOT",
  "org.scalatest" %%% "scalatest" % "3.0.7" % Test)

webpackConfigFile in fullOptJS := Some(baseDirectory.value / "prod.webpack.config.js")

webpackBundlingMode := BundlingMode.LibraryAndApplication()

//scalaJSUseMainModuleInitializer := true

scalaJSModuleKind := ModuleKind.CommonJSModule

//requireJsDomEnv in Test := true

useYarn := true

webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly()

emitSourceMaps:=true  