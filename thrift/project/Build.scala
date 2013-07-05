import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._
import com.github.bigtoast.sbtthrift.ThriftPlugin._

object Builds extends Build {
  lazy val buildSettings = Defaults.defaultSettings ++ Seq(
    version := "1.0",
    organization := "org.tmt",
    scalaVersion := "2.10.2"
  )

  lazy val app = Project("TestThrift", file("."),
    settings = buildSettings ++ assemblySettings ++ thriftSettings) settings(
      mainClass in assembly := Some("test.JavaClient")
//      thriftOutputDir in thrift := file("target/scala-2.10/src_managed/main")
    )
}
