package com.springboot.application.myapplication.service;

import com.springboot.application.myapplication.entity.Student;
import com.springboot.application.myapplication.entity.Teacher;
import com.springboot.application.myapplication.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TeacherServiceImpl implements TeacherService{

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository theTeacherRepository)
    {
        teacherRepository= theTeacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Teacher findById(int theId) {
        Optional<Teacher> result = teacherRepository.findById(theId);

        Teacher theTeacher = null;
        if(result.isPresent())
        {
            theTeacher = result.get();
        }
        else{
            // we didn't find the teacher
            throw new RuntimeException("Did not find teacher id - " + theId);
        }

        return theTeacher;
    }

    @Override
    public void save(Teacher theTeacher) {
        teacherRepository.save(theTeacher);
    }

    @Override
    public void deleteById(int theId) {
        teacherRepository.deleteById(theId);
    }

    @Override
    public Page<Teacher> findPageinated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber -1,pageSize);
        return this.teacherRepository.findAll(pageable);
    }
}