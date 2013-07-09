organization := "org.tmt"

name := "TestMsgPack"

version := "1.0"

scalaVersion := "2.10.2"

// If you build msgpack-java and msgpack-rpc from the sources, the jars end up in the mvn repo
resolvers += Resolver.mavenLocal

libraryDependencies += "org.msgpack" % "msgpack" % "0.6.8-SNAPSHOT"

libraryDependencies += "org.msgpack" % "msgpack-rpc" % "0.7.1-SNAPSHOT"

//libraryDependencies += "org.msgpack" %% "msgpack-scala" % "0.6.7"

//libraryDependencies += "org.msgpack" %% "msgpack-rpc-scala" % "0.6.7-SNAPSHOT"

libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.5"

