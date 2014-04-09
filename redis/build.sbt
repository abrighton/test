
import AssemblyKeys._

name := "TestRedis"

libraryDependencies ++= Seq(
          "net.debasishg"     % "redisclient_2.10"         % "2.12",
          "commons-pool"      %  "commons-pool"            % "1.6",
          "org.scala-lang"    %  "scala-actors"            % "2.10.0",
          "com.typesafe.akka" %  "akka-actor_2.10"         % "2.2.0",
          "org.slf4j"         %  "slf4j-api"               % "1.7.2",
          "org.slf4j"         %  "slf4j-log4j12"           % "1.7.2",
          "log4j"             %  "log4j"                   % "1.2.16")

assemblySettings

