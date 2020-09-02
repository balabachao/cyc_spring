package com.example.demo2.SpringContainer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Configuration.class,ConfigruationB.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person.getName());
    }
}
