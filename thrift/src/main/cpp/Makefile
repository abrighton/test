THRIFT_VER =thrift-0.9.0
USR_DIR    =/usr/local
THRIFT_DIR =${USR_DIR}
INCS_DIRS  =-I${USR_DIR}/include -I${THRIFT_DIR}/include/thrift
LIBS_DIRS  =-L${USR_DIR}/lib -L${USR_DIR}/${THRIFT_VER}/lib
CPP_DEFS   =-D=HAVE_CONFIG_H
CPP_OPTS   =-Wall -O2
LIBS       =-lthrift

GEN_SRC    = ../gen-cpp/BinaryService.cpp  \
             ../gen-cpp/protocol_types.cpp

GEN_INC    = -I../gen-cpp -I../..

default: thrift server

server: CppServer.cpp
	g++ ${CPP_OPTS} ${CPP_DEFS} -o CppServer ${GEN_INC} ${INCS_DIRS} CppServer.cpp ${GEN_SRC} ${LIBS_DIRS} ${LIBS}

clean:
	$(RM) -r CppServer ../gen-cpp

thrift:
	(cd ..; thrift -r --gen cpp thrift/protocol.thrift)
