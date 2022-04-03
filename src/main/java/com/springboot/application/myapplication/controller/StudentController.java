package com.springboot.application.myapplication.controller;

import com.springboot.application.myapplication.entity.Student;
import com.springboot.application.myapplication.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService theStudentService) {
        studentService = theStudentService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    public String listStudents(Model theModel) {

        return findPaginated(1,theModel);
    }

    @GetMapping("/add")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Student theStudent = new Student();

        theModel.addAttribute("student", theStudent);

        return "students/student-form";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("studentRollNo") int theRollNo,
                                    Model theModel) {

        // get the student from the service
        Student theStudent = studentService.findByRollNo(theRollNo);

        // set student as a model attribute to pre-populate the form
        theModel.addAttribute("student", theStudent);

        // send over to our form
        return "students/student-form";
    }


    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student theStudent) {

        // save the student
        studentService.save(theStudent);

        // use a redirect to prevent duplicate submissions
        return "redirect:/students/page/1";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("studentRollNo") int theRollNo) {

        // delete the student
        studentService.deleteByRollNo(theRollNo);

        // redirect to /students/list
        return "redirect:/students/page/1";

    }

    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable(value="pageNumber") int pageNumber,Model model)
    {
        int pageSize=3;

        Page<Student> page = studentService.findPageinated(pageNumber,pageSize);
        List<Student> listStudents = page.getContent();

        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("students",listStudents);

        return "students/students-list";
    }
}