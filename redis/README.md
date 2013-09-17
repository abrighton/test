Redis  Pub/Sub test
===================

This project tests the performance of the Redis publish/subscribe feature.

Build It
--------

To build, run:

   sbt assembly

Then, cd to target/scala-2.10 to find the assembly to run.

Run the Test
------------

Make sure the redis server is running.

To test publisher and subscriber on the local host, run:

  java -jar TestRedis-assembly-1.0.jar sub chan1 localhost 6379

in one terminal window, and:

  java -jar TestRedis-assembly-1.0.jar pub chan1 100000 128 localhost 6379

in another window.

Usage:
------

 First start the subscriber:

   java -jar TestRedis-assembly-1.0.jar sub channel host port

 Then start the publisher:

   java -jar TestRedis-assembly-1.0.jar pub channel count size host port

 Host is the host where the redis server is running (normally on port 6379)
