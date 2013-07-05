#include "BinaryService.h"
#include <thrift/protocol/TBinaryProtocol.h>
#include <thrift/server/TSimpleServer.h>
#include <thrift/transport/TServerSocket.h>
#include <thrift/transport/TBufferTransports.h>

using namespace ::apache::thrift;
using namespace ::apache::thrift::protocol;
using namespace ::apache::thrift::transport;
using namespace ::apache::thrift::server;

using boost::shared_ptr;

using namespace  ::org::tmt::test;

class BinaryServiceHandler : virtual public BinaryServiceIf {
 private:
    

 public:
  BinaryServiceHandler() {
    // Your initialization goes here
  }

  void fetchBlob(std::string& _return, const int32_t numBytes) {
      //printf("fetchBlob %d\n", numBytes);
      _return = std::string(numBytes, 'a'); // XXX could cache string...
  }

};

int main(int argc, char **argv) {
    int port = 9090;
    if (argc == 2) port = atoi(argv[1]);
    shared_ptr<BinaryServiceHandler> handler(new BinaryServiceHandler());
    shared_ptr<TProcessor> processor(new BinaryServiceProcessor(handler));
    shared_ptr<TServerTransport> serverTransport(new TServerSocket(port));
    shared_ptr<TTransportFactory> transportFactory(new TBufferedTransportFactory());
    shared_ptr<TProtocolFactory> protocolFactory(new TBinaryProtocolFactory());

    TSimpleServer server(processor, serverTransport, transportFactory, protocolFactory);
    server.serve();
    return 0;
}

