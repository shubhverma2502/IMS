package com.ims.course.service;

import com.ims.common.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course createCourse(Course student);
    Course updateCourse(Course student);
    Optional<Course> getCourseById(Long id);
    Optional<Course> getCourseByName(String name);
    List<Course> getAllCourses();
    void deleteCourseById(Long id);
    long totalCourses();
    List<Course> getCoursesByInstructorName(String instructorName);
}
