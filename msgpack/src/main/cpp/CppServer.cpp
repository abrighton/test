#include <msgpack/rpc/server.h>

namespace rpc {
	using namespace msgpack;
	using namespace msgpack::rpc;
} 

using namespace mp::placeholders;


class MyServer : public rpc::dispatcher {
public:
	typedef rpc::request request;

	void dispatch(request req)
	try {
		std::string method;
		req.method().convert(&method);

		if(method == "add") {
			msgpack::type::tuple<int> params;
			req.params().convert(&params);
			fetchBlob(req, params.get<0>());

		} else {
			req.error(msgpack::rpc::NO_METHOD_ERROR);
		}

	} catch (msgpack::type_error& e) {
		req.error(msgpack::rpc::ARGUMENT_ERROR);
		return;

	} catch (std::exception& e) {
		req.error(std::string(e.what()));
		return;
	}

	void fetchBlob(request req, int size)
	{
		req.result(std::string(numBytes, 'a'));
	}
};


int main(void){
    signal(SIGPIPE, SIG_IGN);

    rpc::server svr;

    std::auto_ptr<rpc::dispatcher> dp(new MyServer);
    svr.serve(dp.get());

    svr.listen("0.0.0.0", 18811);

    svr.start(1); // arg is number of threads
    std::cout << "XXX started MyServer on port 18811 " << std::endl;
}

