Test of Java or Scala Msgpack Client with a C++ Msgpack server (Not complete)
======================================================

The basic msgpack jar is available from maven repos, but msgpack-rpc does not seem to be anywhere, so it has to be built from source (with mvn).

Notes:
------

msgpack-scala and msgpack-rpc-scala are currently only available for Scala-2.9.x and sbt 0.11 (or only mvn).

I had some trouble getting msgpack-c and its dependencies build on Ubuntu-12.4.

I checked this in for reference, but it is not complete and we decided to use Thrift for now.
