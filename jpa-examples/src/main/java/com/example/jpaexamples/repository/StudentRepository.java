package com.example.jpaexamples.repository;

import com.example.jpaexamples.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
