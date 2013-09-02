import java.nio.ByteBuffer
import org.apache.thrift.server.{TSimpleServer, TServer}
import org.apache.thrift.transport.TServerSocket
import org.tmt.test.BinaryService

/**
 * Scala version of the thrift server
 */
object ScalaServer extends App {

  if (args.length != 1) {
    println("Expected 1 arg (the port number to listen on)")
    sys.exit(1)
  }

  try {
    val port = args(0).toInt
    val handler = new BinaryService.Iface() {
      override def fetchBlob(numBytes: Int): ByteBuffer = {
        val ar = new Array[Byte](numBytes)
        val b = "a".getBytes()(0)
        java.util.Arrays.fill(ar, b)
        ByteBuffer.wrap(ar)
      }
    }

    val processor = new BinaryService.Processor(handler)
    val serverTransport = new TServerSocket(port)
    val server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor))

    ScalaServerImpl(server).start(port)
  } catch {
    case x: Exception => x.printStackTrace()
  }

  case class ScalaServerImpl(private val _server: TServer) {
    def start(port: Int): Unit =
      try {
        _server.serve()
      } catch {
        case x: Exception => x.printStackTrace()
      }

    def stop(): Unit = _server.stop()
  }
}

