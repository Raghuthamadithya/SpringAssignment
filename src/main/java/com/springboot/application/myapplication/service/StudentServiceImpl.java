package com.springboot.application.myapplication.service;

import com.springboot.application.myapplication.entity.Student;
import com.springboot.application.myapplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository theStudentRepository)
    {
        studentRepository= theStudentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Student findByRollNo(int theRollNo) {
        Optional<Student> result = studentRepository.findById(theRollNo);

        Student theStudent = null;
        if(result.isPresent())
        {
            theStudent = result.get();
        }
        else{
            // we didn't find the student
            throw new RuntimeException("Did not find student id - " + theRollNo);
        }

        return theStudent;
    }

    @Override
    public void save(Student theStudent) {
        studentRepository.save(theStudent);
    }

    @Override
    public void deleteByRollNo(int theRollNo) {
        studentRepository.deleteById(theRollNo);
    }

    @Override
    public Page<Student> findPageinated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber -1,pageSize);
        return this.studentRepository.findAll(pageable);
    }

}