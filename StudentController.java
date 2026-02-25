package com.example.week5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.week5.entity.Student;
import com.example.week5.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	 @Autowired
	    private StudentService service;

	    @PostMapping
	    public Student addStudent(@RequestBody Student student) {
	        return service.saveStudent(student);
	    }

	    @GetMapping
	    public List<Student> getStudents() {
	        return service.getAllStudents();
	    }

	    @DeleteMapping("/{id}")
	    public String deleteStudent(@PathVariable Long id) {
	        service.deleteStudent(id);
	        return "Student Deleted";
	    }

}
