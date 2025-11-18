package com.ims.student.controller;

import com.ims.common.entity.Student;
import com.ims.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentApiController {

    @Autowired
    StudentService
            studentService;

    // Create student
    @PostMapping("/create")
    public ResponseEntity<?> saveStudent(@Valid @RequestBody Student student){
        Student newStudent = studentService.createStudent(student);
        if(newStudent != null){
            return ResponseEntity.ok(newStudent);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student already exist with given email");
        }
    }

    // Update student by Id
    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student){
        try {
            Student updated = studentService.updateStudent(student);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    // Get student by Id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get student by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
        return studentService.getStudentByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all students
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // Delete student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("Student with ID " + id + " deleted successfully.");
    }

    // count students
    @GetMapping("/count")
    public ResponseEntity<String> getTotalStudentCount() {
        long count = studentService.getTotalStudents();
        return ResponseEntity.ok("Total Students : " + count);
    }

    // search student by name and course
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam String keyword) {
        List<Student> results = studentService.searchStudents(keyword);
        return ResponseEntity.ok(results);
    }
}
