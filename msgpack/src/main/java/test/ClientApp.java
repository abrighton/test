package test;

import org.msgpack.rpc.Client;
import org.msgpack.rpc.loop.EventLoop;

public class ClientApp {
    public static interface RPCInterface {
        String hello(String msg, int a);
    }

    public static void main(String[] args) throws Exception {
        EventLoop loop = EventLoop.defaultEventLoop();

        Client cli = new Client("localhost", 1985, loop);
        RPCInterface iface = cli.proxy(RPCInterface.class);

        iface.hello("hello", 1);
    }
}