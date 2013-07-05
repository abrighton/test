#
# Defines the client server interface
#

namespace cpp org.tmt.test
namespace java org.tmt.test

service BinaryService {
  binary fetchBlob(1: i32 numBytes)
}
