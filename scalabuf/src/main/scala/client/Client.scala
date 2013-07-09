package client

import examples.{SearchResponse, SearchRequest}
import com.google.protobuf.{CodedOutputStream, CodedInputStream}
import java.net.Socket

/**
 * Test Scala Client
 */
object Client {

  def main(args: Array[String]) {
    val socket = new Socket("linux", 5555)
//    val in = CodedInputStream.newInstance(socket.getInputStream)
//    val out = CodedOutputStream.newInstance(socket.getOutputStream)
    val request = SearchRequest("test", Some(0))
//    request.writeTo(out)
    request.writeDelimitedTo(socket.getOutputStream)
//    val response = SearchResponse.getDefaultInstance.mergeFrom(in)
    val response = SearchResponse.getDefaultInstance.mergeDelimitedFromStream(socket.getInputStream)
    println(s"XXX response = $response")
  }
}
