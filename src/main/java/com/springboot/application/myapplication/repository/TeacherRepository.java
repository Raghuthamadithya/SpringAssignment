package com.springboot.application.myapplication.repository;

import com.springboot.application.myapplication.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    // that's it ... no need to write any code LOL!

    // add a method to sort by last name

    List<Teacher> findAllByOrderByLastNameAsc();
}