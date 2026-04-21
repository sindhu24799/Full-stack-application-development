package com.example.productapi.model;

import jakarta.validation.constraints.*;

public class User {

    @NotEmpty(message="Name cannot be empty")
    private String name;

    @Email(message="Invalid email")
    private String email;

    @Min(value=18, message="Age must be 18+")
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}