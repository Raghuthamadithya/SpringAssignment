package com.springboot.application.myapplication.service;

import com.springboot.application.myapplication.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;


public interface StudentService {

    public List<Student> findAll();

    public Student findByRollNo(int theRollNo);

    public void save(Student theStudent);

    public void deleteByRollNo(int theRollNo);

    Page<Student> findPageinated(int pageNumber, int pageSize);

}