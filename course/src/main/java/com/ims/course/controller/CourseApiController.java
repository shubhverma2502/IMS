package com.ims.course.controller;

import com.ims.common.entity.Course;
import com.ims.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseApiController {
    @Autowired
    CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<?> saveStudent(@Valid @RequestBody Course course){
        Course newCourse = courseService.createCourse(course);
        if(newCourse != null){
            return ResponseEntity.ok(newCourse);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course already exist with given name");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Course>> getAllStudents() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/count")
    public ResponseEntity<String> getTotalCourseCount() {
        long count = courseService.totalCourses();
        return ResponseEntity.ok("Total Courses : " +count);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCoursesByInstructor(@RequestParam String instructorName) {
        List<Course> courses = courseService.getCoursesByInstructorName(instructorName);
        return ResponseEntity.ok(courses);
    }

}
