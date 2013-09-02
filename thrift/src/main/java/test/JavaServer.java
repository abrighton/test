package test;

import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.tmt.test.BinaryService;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Java version of the thrift server
 */
public class JavaServer {

    private BinaryService.Processor<BinaryService.Iface> _processor;
    private TServer _server;

    public JavaServer(BinaryService.Processor<BinaryService.Iface> processor) {
        _processor = processor;
    }

    public void start(int port) {
        try {
            TServerTransport serverTransport = new TServerSocket(port);
            _server = new TSimpleServer(new TServer.Args(serverTransport).processor(_processor));

            _server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        _server.stop();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Expected 1 arg (the port number to listen on)");
            System.exit(1);
        }
        try {
            int port = Integer.valueOf(args[0]);
            BinaryService.Iface handler = new BinaryService.Iface() {
                @Override
                public ByteBuffer fetchBlob(int numBytes) throws TException {
                    byte[] ar = new byte[numBytes];
                    Arrays.fill(ar, "a".getBytes()[0]);
                    return ByteBuffer.wrap(ar);
                }
            };
            BinaryService.Processor<BinaryService.Iface> processor = new BinaryService.Processor<BinaryService.Iface>(handler);
            new JavaServer(processor).start(port);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}