/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package test;

// Generated code
import org.tmt.test.*;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

public class JavaClient {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Expected 4 args: host port count blobSize");
            System.exit(1);
        }
        try {
            String host = args[0];
            int port = Integer.valueOf(args[1]);
            int count = Integer.valueOf(args[2]);
            int size = Integer.valueOf(args[3]);

            TTransport transport = new TSocket(host, port);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            BinaryService.Client client = new BinaryService.Client(protocol);

            perform(client, count, size);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(BinaryService.Client client, int count, int size) throws TException {
        long t = System.currentTimeMillis();
        for(int i = 0; i < count; i++) {
            client.fetchBlob(size);
        }
        double secs = (System.currentTimeMillis() - t)/1000.0;
        System.out.println("Transfered " + count + " " + size + " byte blobs in " + secs + " seconds");
    }
}
