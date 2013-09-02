
import AssemblyKeys._
import com.github.bigtoast.sbtthrift.ThriftPlugin

organization := "org.tmt"

name := "TestThrift"

version := "1.0"

scalaVersion := "2.10.2"


libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.5"

libraryDependencies += "org.apache.thrift" % "libthrift" % "0.9.1"

seq(ThriftPlugin.thriftSettings: _*)

//seq(ThriftPlugin.thriftSettings: _*) ++ Seq(ThriftPlugin.thriftOutputDir := file("target/scala-2.10/src_managed/"))

//ThriftPlugin.thriftSettings ++ Seq(ThriftPlugin.thriftOutputDir := file("target/scala-2.10/src_managed/main"))


assemblySettings

mainClass in assembly := Some("test.JavaClient")
