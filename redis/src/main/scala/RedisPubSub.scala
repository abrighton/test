import akka.actor.{ ActorSystem, Props }
import com.redis._
import java.util.Date

/**
 * Tests the Redis subscribe/publish performance.
 *
 * If the first arg is "pub", $count strings of length $size are published to $channel.
 * If the first arg is "sub", the app subscripts to the given $channel.
 */
object RedisPubSub extends App {
  if (args.length < 4) usage()
  try {
    args(0) match {
      case "pub" =>
        if (args.length != 6) usage()
        pub(args(1), args(2).toInt, args(3).toInt, args(4), args(5).toInt)

      case "sub" =>
        if (args.length != 4) usage()
        sub(args(1), args(2), args(3).toInt)
    }
  } catch {
    case ex: NumberFormatException => usage()
  }

  def usage() :Unit = {
    println(
      """
        | Usage:
        |
        | First start the subscriber:
        |
        |   RedisPubSub sub channel host port
        |
        | Then start the publisher:
        |
        |   RedisPubSub pub channel count size host port
        |
        | Host is the host where the redis server is running (normally on port 6379)
        |
      """.stripMargin)
    System.exit(1)
  }

  /**
   * Publish count messages of the given size to the given channel
   */
  def pub(channel: String, count: Int, size: Int, host: String, port: Int): Unit = {
    val p = Pub(host, port)
    val msg = "a"*size // aaaa...

    val startingTime = new Date().getTime

    for(i <- 1 to count) {
      p.publish(channel, msg)
    }

    val elapsedTime = (new Date().getTime - startingTime)/1000.0
    println(s"\nDone: Published $count messages in $elapsedTime seconds")

    p.publish(channel, "exit")
  }

  /**
   * Subscribes to the given channel
   */
  def sub(channel: String, host: String, port: Int): Unit = {
    val s = Sub(host, port)
    s.sub(channel)
  }
}


// -- Modified from PubSubServerDemo in RedisClient test dir  --

case class Pub(host: String = "localhost", port: Int = 6379) {
  println("starting publishing service ..")
  val system = ActorSystem("pub")
  val r = new RedisClient(host, port)
  val p = system.actorOf(Props(new Publisher(r)))

  def publish(channel: String, message: String) = {
    p ! Publish(channel, message)
  }
}

case class Sub(host: String = "localhost", port: Int = 6379) {
  println("starting subscription service ..")
  val system = ActorSystem("sub")
  val r = new RedisClient(host, port)
  val s = system.actorOf(Props(new Subscriber(r)))
  s ! Register(callback)
  var startingTime = 0L
  var count = 0

  def sub(channels: String*) = {
    s ! Subscribe(channels.toArray)
  }

  def unsub(channels: String*) = {
    s ! Unsubscribe(channels.toArray)
  }

  def callback(pubsub: PubSubMessage) = pubsub match {
    case E(exception) => println("Fatal error caused consumer dead. Please init new consumer reconnecting to master or connect to backup")
    case S(channel, no) =>
      println("subscribed to " + channel + " and count = " + no)
    case U(channel, no) =>
      println("unsubscribed from " + channel + " and count = " + no)
      System.exit(0)
    case M(channel, msg) =>
      msg match {
        // exit will unsubscribe from all channels and stop subscription service
        case "exit" =>
          val elapsedTime = (new Date().getTime - startingTime)/1000.0
          println(s"\nDone: Received $count messages in $elapsedTime seconds")
          r.unsubscribe

//        // message "+x" will subscribe to channel x
//        case x if x startsWith "+" =>
//          val s: Seq[Char] = x
//          s match {
//            case Seq('+', rest @ _*) => r.subscribe(rest.toString()){ m => }
//          }
//
//        // message "-x" will unsubscribe from channel x
//        case x if x startsWith "-" =>
//          val s: Seq[Char] = x
//          s match {
//            case Seq('-', rest @ _*) => r.unsubscribe(rest.toString())
//          }

        // other message receive
        case x =>
          if (count == 0) startingTime = new Date().getTime
//          println("received message on channel " + channel + " as : " + x)
          count = count + 1
      }
  }
}
