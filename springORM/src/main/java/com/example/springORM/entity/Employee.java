package com.example.springORM.entity;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int id;

    private String name;
}