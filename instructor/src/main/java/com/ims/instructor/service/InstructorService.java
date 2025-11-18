package com.ims.instructor.service;

import com.ims.common.entity.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorService {
    Instructor createInstructor(Instructor instructor);
    Instructor updateInstructor(Instructor instructor);
    Optional<Instructor> getInstructorById(Long id);
    Optional<Instructor> getInstructorByName(String name);
    List<Instructor> getAllInstructor();
    void deleteStudentById(Long id);
    long totalInstructor();
}
