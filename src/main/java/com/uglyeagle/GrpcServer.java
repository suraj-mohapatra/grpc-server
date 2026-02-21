package com.uglyeagle;

import com.uglyeagle.service.ExampleService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

    public static void main(String[] args) throws Exception {

        Short port = 8756;

        Server server = ServerBuilder
                .forPort(port) // defines port
                .addService(new ExampleService()) // register the implemented here
                .build();

        server.start();

        System.out.println("gRPC server started on port " + port);

        server.awaitTermination();
    }

}
