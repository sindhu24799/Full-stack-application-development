package com.example.borrowservice.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

@Autowired
RestTemplate restTemplate;

@GetMapping("/{id}")
public Map<String,Object> borrowBook(@PathVariable int id){

Map book=
restTemplate.getForObject(
"http://BOOK-SERVICE/book/"+id,
Map.class);

Map user=
restTemplate.getForObject(
"http://USER-SERVICE/user/"+id,
Map.class);

Map<String,Object> result=new HashMap<>();

result.put("book",book);
result.put("user",user);
result.put("status","Borrow Approved");

return result;

}

}