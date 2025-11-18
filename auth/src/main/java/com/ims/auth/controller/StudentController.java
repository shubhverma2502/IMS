package com.ims.auth.controller;

import com.ims.common.entity.Course;
import com.ims.common.entity.Student;
import com.ims.common.repository.CourseRepository;
import com.ims.course.service.CourseService;
import com.ims.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String getStudentDetails(Model model) {

        List<Student> studentList = studentService.getAllStudents();
        model.addAttribute("studentList", studentList);
        return "student/students";
    }

    @GetMapping("/students/add-form")
    public String showAddStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);

        List<Course> courseList = courseService.getAllCourses();
        model.addAttribute("courses", courseList);
        return "student/add-student-form";
    }

    @PostMapping("/students/add-student")
    public String addStudent(@ModelAttribute("student") @Valid Student student,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("courses", courseRepository.findAll());
            return "add-student";
        }
        try {
            studentService.createStudent(student);
            redirectAttributes.addFlashAttribute("successMessage", "Student added successfully!");
            return "redirect:/students";
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "error.student", e.getMessage());
            model.addAttribute("courses", courseRepository.findAll());
            return "student/add-student-form";
        }
    }

    @GetMapping("students/get-students-form/{studentId}")
    public String getEditStudentForm(@PathVariable("studentId") Long studentId, Model model) {
        Optional<Student> studentOptional = studentService.getStudentById(studentId);

        studentOptional.ifPresent(student -> model.addAttribute("student", student));

        if (studentOptional.isPresent()) {
            model.addAttribute("student", studentOptional.get());
        } else {
            return "redirect:/students";
        }

        return "student/edit-student-form";
    }
}
