package com.springboot.application.myapplication.service;

import com.springboot.application.myapplication.entity.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {

    public List<Teacher> findAll();

    public Teacher findById(int theId);

    public void save(Teacher theTeacher);

    public void deleteById(int theId);

    Page<Teacher> findPageinated(int pageNumber, int pageSize);

}
