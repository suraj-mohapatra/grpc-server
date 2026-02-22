package com.uglyeagle.service;

import com.uglyeagle.grpc.Example.Empty;
import com.uglyeagle.grpc.Example.ExampleRq;
import com.uglyeagle.grpc.Example.ExampleRs;
import com.uglyeagle.grpc.ExampleServiceGrpc;

import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class ExampleService extends ExampleServiceGrpc.ExampleServiceImplBase {

    @Override
    public void exampleOne(ExampleRq request,
                           StreamObserver<ExampleRs> responseObserver) {

        String param1 = request.getRqParam1();
        String param2 = request.getRqParam2();

        System.out.println("exampleOne called with: " + param1 + ", " + param2);

        ExampleRs response = ExampleRs.newBuilder()
                .setRsMessage("Received: " + param1 + " and " + param2)
                .setRsCode(200)
                .build();

        responseObserver.onNext(response); // send response

        responseObserver.onCompleted();   // close the rpc call, not the TCP connection
    }


    @Override
    public void exampleTwo(Empty request,
                           StreamObserver<ExampleRs> responseObserver) {

        System.out.println("exampleTwo called");

        ExampleRs response = ExampleRs.newBuilder()
                .setRsMessage("exampleTwo executed successfully")
                .setRsCode(200)
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }

}