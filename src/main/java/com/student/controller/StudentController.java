package com.student.controller;


import com.student.StudentProperties;
import com.student.core.Student;
import com.student.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Inject
    private StudentProperties studentProperties;

    @Inject
    private StudentService studentService;

    @GetMapping("/msg")
    public String getMessage(@RequestHeader("user-agent") String userAgent) {
        return studentProperties.getGreeting() + userAgent;
    }

    @GetMapping
    public Collection<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id")  long id){
        return studentService.get(id);
    }

    @GetMapping("/single")
    public Student getSingelStudent(@RequestParam("id") Optional<Long> id){
        return studentService.get(id.orElse(1L));
    }

    @GetMapping("/search/{department}")
    public Collection<Student> getStudentsPerDepartment(@PathVariable("department") String department
                                            , @RequestParam("name") String name){
        return studentService.getAllStudentsInDepartment(department, name);
    }

}
