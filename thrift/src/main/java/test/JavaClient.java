package test;

// Generated code
import org.tmt.test.*;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

/**
 * This is the client part of a performance test. The server is a C++ application in
 * ../../cpp/CppServer.cpp.
 * The test sends requests to the server and prints the time it takes to get the result.
 * The requests include the size of the answer from the server in bytes.
 * This client test takes the host, port and a count of requests to send and then
 * loops through the different request sizes: 32, 64, 256, 1024, and performs the test
 * "count" times for each request.
 */
public class JavaClient {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Expected 3 args: host port count");
            System.exit(1);
        }
        String host = args[0];
        int port = Integer.valueOf(args[1]);
        int count = Integer.valueOf(args[2]);
        TTransport transport = new TSocket(host, port);
        try {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            BinaryService.Client client = new BinaryService.Client(protocol);

            // just to warm up, get stuff in memory...
            perform(client, count, 32, false);

            // Try different size requests
            int[] ar = new int[]{32, 64, 256, 1024};
            long t = System.currentTimeMillis();
            for (int size : ar) {
                perform(client, count, size, true);
            }
            double secs = (System.currentTimeMillis() - t) / 1000.0;
            System.out.println((count*ar.length/secs) + " Total requests/sec");
            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    // Sends the request to the server and prints timing statistics.
    private static void perform(BinaryService.Client client, int count, int size, boolean print) throws TException {
        long t = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            client.fetchBlob(size);
        }
        if (print) {
            double secs = (System.currentTimeMillis() - t) / 1000.0;
            System.out.println("Transfered " + count + " " + size + " byte blobs in " + secs + " seconds = "
                    + (count * size / secs) + " bytes/sec, " + (count / secs) + " requests/sec");
        }
    }
}
