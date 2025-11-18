package com.ims.auth.controller;

import com.ims.common.entity.Course;
import com.ims.common.entity.Instructor;
import com.ims.common.entity.Student;
import com.ims.common.repository.CourseRepository;
import com.ims.common.repository.StudentRepository;
import com.ims.course.service.CourseService;
import com.ims.instructor.service.InstructorService;
import com.ims.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    StudentService studentService;

    @Autowired
    InstructorService instructorService;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("activePage", "home");
        return "home";
    }

//    @GetMapping("/students")
//    public String showStudentsPage(Model model) {
//        List<Student> students = studentService.getAllStudents();
//        model.addAttribute("students", students);
//        model.addAttribute("activePage", "students");
//        return "student/students";
//    }

    @GetMapping("/instructors")
    public String showInstructorsPage(Model model) {
        List<Instructor> instructors = instructorService.getAllInstructor();
        model.addAttribute("instructor", instructors);
        model.addAttribute("activePage", "instructors");
        return "instructors/instructor";
    }

    @GetMapping("/courses")
    public String showCoursesPage(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("course", courses);
        model.addAttribute("activePage", "courses");
        return "courses/course";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("activePage", "about");
        return "about";
    }
//    @GetMapping("/students/new")
//    public String showAddStudentForm(Model model) {
//        Student student = new Student();
//        model.addAttribute("student", student);
//
//        List<Course> courseList = courseService.getAllCourses();
//        model.addAttribute("courses", courseList);
//        return "add-student";
//    }

//    @PostMapping("/students/add")
//    public String saveStudent(@ModelAttribute("student") Student student,
//                              @RequestParam("courseId") Long courseId,
//                              RedirectAttributes redirectAttributes) {
//
//        System.out.println("Received courseId: " + courseId);
//
//        Optional<Course> fullCourseOpt = courseService.getCourseById(courseId);
//
//        if (fullCourseOpt.isPresent()) {
//            student.setCourse(fullCourseOpt.get());
//            studentService.createStudent(student);
//            redirectAttributes.addFlashAttribute("successMessage", "Student created successfully!");
//            return "redirect:/students";
//        } else {
//            redirectAttributes.addFlashAttribute("errorMessage", "Course not found!");
//            return "redirect:/students/add";
//        }
//    }

//    @PostMapping("/students/add")
//    public String addStudent(@ModelAttribute("student") @Valid Student student,
//                             BindingResult result,
//                             Model model,
//                             RedirectAttributes redirectAttributes) {
//
//        if (result.hasErrors()) {
//            model.addAttribute("courses", courseRepository.findAll());
//            return "add-student";
//        }
//        try {
//            studentService.createStudent(student);
//            redirectAttributes.addFlashAttribute("successMessage", "Student added successfully!");
//            return "redirect:/students";
//        } catch (IllegalArgumentException e) {
//            result.rejectValue("email", "error.student", e.getMessage());
//            model.addAttribute("courses", courseRepository.findAll());
//            return "add-student";
//        }
//    }

//    @GetMapping("/instructor/new")
//    public String showAddInstructorForm(Model model){
//        Instructor instructor = new Instructor();
//        model.addAttribute("instructor",instructor);
//        return "add-instructor";
//
//    }
//
//    @PostMapping("/instructor/add")
//    public String addInstructor(@ModelAttribute("instructor") @Valid Instructor instructor,
//                                BindingResult result,
//                                Model model,
//                                RedirectAttributes redirectAttributes){
//        if (result.hasErrors()) {
//            return "add-instructor";
//        }
//        try{
//            instructorService.createInstructor(instructor);
//            redirectAttributes.addFlashAttribute("successMessage", "Instructor added successfully!");
//            return "redirect:/instructors";
//        }catch (IllegalArgumentException e){
//            result.rejectValue("email", "error.instructor", e.getMessage());
//            return "add-instructor";
//        }
//    }



}

