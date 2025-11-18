package com.ims.student.service;

import com.ims.common.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student createStudent(Student student);
    Student updateStudent(Student student);
    Optional<Student> getStudentById(Long id);
    Optional<Student> getStudentByName(String name);
    List<Student> getAllStudents();
    void deleteStudentById(Long id);
    long getTotalStudents();
    List<Student> searchStudents(String keyword);
}
