
import AssemblyKeys._

//organization := "org.tmt"

name := "TestRedis"

//version := "1.0"

//scalaVersion := "2.10.2"


libraryDependencies ++= Seq(
          "net.debasishg"     % "redisclient_2.10"         % "2.10",
          "commons-pool"      %  "commons-pool"            % "1.6",
          "org.scala-lang"    %  "scala-actors"            % "2.10.0",
          "com.typesafe.akka" %  "akka-actor_2.10"         % "2.1.0",
          "org.slf4j"         %  "slf4j-api"               % "1.7.2",
          "org.slf4j"         %  "slf4j-log4j12"           % "1.7.2"      % "provided",
          "log4j"             %  "log4j"                   % "1.2.16"     % "provided")


assemblySettings

//mainClass in assembly := Some("test.RedisPubSub")
