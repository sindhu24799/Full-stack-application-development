package com.example.week5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.week5.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
