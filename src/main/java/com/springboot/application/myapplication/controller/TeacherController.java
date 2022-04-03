package com.springboot.application.myapplication.controller;

import com.springboot.application.myapplication.entity.Student;
import com.springboot.application.myapplication.entity.Teacher;
import com.springboot.application.myapplication.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService theTeacherService) {
        teacherService = theTeacherService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    public String listTeachers(Model theModel) {

        return findPaginated(1,theModel);
    }

    @GetMapping("/add")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Teacher theTeacher = new Teacher();

        theModel.addAttribute("teacher", theTeacher);

        return "teachers/teacher-form";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("teacherId") int theId,
                                    Model theModel) {

        // get the teacher from the service
        Teacher theTeacher = teacherService.findById(theId);

        // set teacher as a model attribute to pre-populate the form
        theModel.addAttribute("teacher", theTeacher);

        // send over to our form
        return "teachers/teacher-form";
    }


    @PostMapping("/save")
    public String saveTeacher(@ModelAttribute("teacher") Teacher theTeacher) {

        // save the teacher
        teacherService.save(theTeacher);

        // use a redirect to prevent duplicate submissions
        return "redirect:/teachers/page/1";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("teacherId") int theId) {

        // delete the teacher
        teacherService.deleteById(theId);

        // redirect to /teachers/list
        return "redirect:/teachers/page/1";

    }

    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable(value="pageNumber") int pageNumber,Model model)
    {
        int pageSize=3;

        Page<Teacher> page = teacherService.findPageinated(pageNumber,pageSize);
        List<Teacher> listTeachers = page.getContent();

        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("teachers",listTeachers);

        return "teachers/teachers-list";
    }
}