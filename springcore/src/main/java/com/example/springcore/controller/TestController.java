package com.example.springcore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcore.service.GreetingService;

@RestController
public class TestController {

    @Autowired
    GreetingService service;

    @GetMapping("/greet")
    public String greet(){
        return service.greet();
    }
}