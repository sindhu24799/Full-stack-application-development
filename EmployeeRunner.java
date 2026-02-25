package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRunner implements CommandLineRunner {
	private final Employee employee;

    public EmployeeRunner(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void run(String... args) {
        System.out.println(employee.getDetails());
    }

}
