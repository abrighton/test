USR_DIR    = /opt/local
INCS_DIRS  =-I${USR_DIR}/include
LIBS_DIRS  =-L${USR_DIR}/lib
CPP_DEFS   =-D=HAVE_CONFIG_H
CPP_OPTS   =-Wall -O2
LIBS       =-lmsgpack -lmsgpack-rpc

default: CppServer.cpp
	g++ ${CPP_OPTS} ${CPP_DEFS} -o CppServer ${INCS_DIRS} CppServer.cpp ${LIBS_DIRS} ${LIBS}

clean:
	$(RM) -r CppServer

