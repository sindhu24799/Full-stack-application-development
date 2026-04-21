package com.example.paymentservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

@PostMapping("/pay")
public Map<String,Object> pay(){

Map<String,Object> res=new HashMap<>();

res.put("payment","success");

return res;

}
}