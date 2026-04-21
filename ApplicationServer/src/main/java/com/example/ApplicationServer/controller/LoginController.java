package com.example.ApplicationServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApplicationServer.service.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    LoginService service;

    @PostMapping("/login")
    public String login(){
        return service.login();
    }
}