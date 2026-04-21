package com.example.productapi.controller;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.productapi.model.Product;

import com.example.productapi.model.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/products")
public class ProductController {

    List<Product> productList = new ArrayList<>();

    // POST
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        productList.add(product);
        return product;
    }

    // GET
    @GetMapping
    public List<Product> getAllProducts() {
        return productList;
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {

        for(Product p : productList) {
            if(p.getId() == id)
                return p;
        }

        return null;
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id,
                                 @RequestBody Product product) {

        for(Product p : productList) {

            if(p.getId() == id) {
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                return p;
            }
        }

        return null;
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {

        productList.removeIf(p -> p.getId()==id);

        return "Product deleted";
    }
    @GetMapping("/search")
    public String searchProduct(@RequestParam String name) {

        return "Searching product: " + name;
    }
    @GetMapping("/hello/{username}")
    public String helloUser(@PathVariable String username) {

        return "Hello " + username;
    }
    @PostMapping("/test-body")
    public Product testBody(@RequestBody Product product) {

        return product;
    }
    @GetMapping("/status")
    public ResponseEntity<String> checkStatus() {

        return ResponseEntity
                .status(200)
                .body("API is working");
    }
    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody User user) {

        return "User registered successfully";
    }

}