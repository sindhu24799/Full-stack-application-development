package com.example.springcore.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

    public String greet(){
        return "Hello from Spring Core";
    }
}