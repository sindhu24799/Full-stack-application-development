package com.example.bookservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

@GetMapping("/{id}")
public Map<String,Object> getBook(@PathVariable int id){

Map<String,Object> book=new HashMap<>();

book.put("bookId",id);
book.put("title","Microservices Guide");
book.put("author","Sam Newman");

return book;

}

}