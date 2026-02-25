package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Employee;
@RestController
public class EmployeeController {
	 @Autowired
	    private Employee employee;

	    @GetMapping("/employee")
	    public String showEmployee() {
	        return employee.getDetails();
	    }

}
