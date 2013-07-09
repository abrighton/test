import sbt._
import Keys._
import scalabuff.ScalaBuffPlugin._

object build extends Build {
  val dependencies = Seq(
    "net.sandrogrzicic" % "scalabuff-runtime_2.10" % "1.3.2-SNAPSHOT",
    //"com.googlecode.protobuf-rpc-pro" % "protobuf-rpc-pro-duplex" % "3.0.4"
  )

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := "org.tmt",
    organizationName := "TMT",
    organizationHomepage := Some(url("http://www.tmt.org")),
    version := "1.0",
    scalaVersion := "2.10.2",
    crossPaths := false,
    resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/akka-snapshots",
    resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
    libraryDependencies ++= dependencies
  )

  lazy val root = Project("TestScalabuf", file("."), settings = buildSettings ++ scalabuffSettings).configs(ScalaBuff)
}
