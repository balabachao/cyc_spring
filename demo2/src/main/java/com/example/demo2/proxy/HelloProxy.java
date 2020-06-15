package com.example.demo2.proxy;

public class HelloProxy implements HelloInterface {
    HelloInterface helloInterface  = new Hello();

    @Override
    public void sayhello() {
        System.out.println("Before Invole sayHello");
        helloInterface.sayhello();
        System.out.println("After invoke sayHello");
    }

    public static void main(String[] args) {
        HelloProxy helloProxy =new HelloProxy();
        helloProxy.sayhello();
    }
}
