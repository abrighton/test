Thrift Client/Server test
=========================

This directory contains a Thrift C++ server and a Java client for testing performance.

C++ Server
----------

The C++ server build requires Thrift (latest version is 0.9.0) to already be installed (in /usr/local, or edit the Makefile).
To build, run "make" in src/main/cpp. The server can be run from the same directory with:

  ./CppServer

The port defaults to 9090, but can be specified:

  ./CppServer 9898

Java Client
-----------

The java client can be build with "sbt assembly" and run from the target/scala-2.10 directory with:

  java -jar TestThrift-assembly-1.0.jar serverHost 9090 1024

Where the arguments are the server hostname, port number, and count (number of times to make the request).
The client then makes "count" requests to the server, for each of the message sizes: 32, 64, 256, and 1024 bytes.
The elapsed time is printed for for each set and the average at the end.
