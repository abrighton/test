import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

object Builds extends Build {
  lazy val buildSettings = Defaults.defaultSettings ++ Seq(
    version := "1.0",
    organization := "org.tmt",
    scalaVersion := "2.10.3"
  )

  lazy val app = Project("TestRedis", file("."),
    settings = buildSettings ++ assemblySettings) settings(
      mainClass in assembly := Some("test.RedisPubSub")
    )
}
