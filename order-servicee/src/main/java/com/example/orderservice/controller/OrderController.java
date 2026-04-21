package com.example.orderservice.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

@PostMapping("/create")
public Map<String,Object> createOrder(){

RestTemplate rt=new RestTemplate();

Map payment=rt.postForObject(
"http://localhost:8201/payment/pay",
null,
Map.class);

Map<String,Object> order=new HashMap<>();

order.put("order","created");
order.put("payment",payment);

return order;

}

}