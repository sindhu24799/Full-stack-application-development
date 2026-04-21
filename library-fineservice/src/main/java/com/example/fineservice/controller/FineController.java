package com.example.fineservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fine")
public class FineController {

@GetMapping("/{days}")
public Map<String,Object> calculateFine(@PathVariable int days){

int fine=days*10;

Map<String,Object> result=new HashMap<>();

result.put("daysLate",days);
result.put("fineAmount",fine);

return result;

}

}