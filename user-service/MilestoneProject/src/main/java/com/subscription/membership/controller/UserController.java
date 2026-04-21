package com.subscription.membership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.subscription.membership.entity.User;
import com.subscription.membership.repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
	@Autowired
    private UserRepository userRepo;

    @PostMapping("/signup")
    public User signup(@RequestBody User user){
        return userRepo.save(user);
    }

}
