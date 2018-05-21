package com.dais39.samplegrpcspringboot;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/grpc/{name}")
    public String hello(@PathVariable String name) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext(true)
                .build();

        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();

        HelloReply reply = stub.sayHello(request);

        System.out.println("Reply: " + reply);

        return reply.getMessage();
    }
}
