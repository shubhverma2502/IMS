package com.ims.instructor.controller;

import com.ims.common.entity.Instructor;
import com.ims.instructor.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorApiController {
    @Autowired
    InstructorService instructorService;
    @PostMapping("/create")
    public ResponseEntity<?> saveInstructor(@Valid @RequestBody Instructor instructor){
        Instructor newInstructor = instructorService.createInstructor(instructor);
        if(newInstructor != null){
            return ResponseEntity.ok(newInstructor);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Instructor already exist with given email");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Instructor>> getAllStudents() {
        List<Instructor> instructors = instructorService.getAllInstructor();
        return ResponseEntity.ok(instructors);
    }
    @GetMapping("/count")
    public ResponseEntity<String> getTotalInstructorCount() {
        long count = instructorService.totalInstructor();
        return ResponseEntity.ok("Total Instructor :" +count);
    }
}
