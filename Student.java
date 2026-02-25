package com.example.week5.entity;
import jakarta.persistence.*;

@Entity
@Table(name="students")

public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "age")
    private int age;
}
