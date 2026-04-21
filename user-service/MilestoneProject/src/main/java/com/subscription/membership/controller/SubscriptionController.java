package com.subscription.membership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import com.subscription.membership.entity.*;
import com.subscription.membership.repository.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SubscriptionController {
	@Autowired
    private UserRepository userRepo;

    @Autowired
    private PlanRepository planRepo;

    @Autowired
    private SubscriptionRepository subRepo;

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        return userRepo.save(user);
    }

    @PostMapping("/createPlan")
    public Plan createPlan(@RequestBody Plan plan){
        return planRepo.save(plan);
    }

    @PostMapping("/subscribe/{userId}/{planId}")
    public Subscription subscribe(@PathVariable Long userId,
                                  @PathVariable Long planId){

        User user = userRepo.findById(userId).orElseThrow();
        Plan plan = planRepo.findById(planId).orElseThrow();

        Subscription sub = new Subscription();
        sub.setUser(user);
        sub.setPlan(plan);
        sub.setStartDate(LocalDate.now());
        sub.setExpiryDate(LocalDate.now().plusMonths(1));
        sub.setStatus("ACTIVE");

        return subRepo.save(sub);
    }
	

}
