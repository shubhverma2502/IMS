package com.ims.course.service;

import com.ims.common.entity.Course;
import com.ims.common.entity.Instructor;
import com.ims.common.repository.CourseRepository;
import com.ims.common.repository.InstructorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CourseServiceImpl implements CourseService{
    private static final Logger LOG = LoggerFactory.getLogger(CourseServiceImpl.class);
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public Course createCourse(Course course) {
        var existingCourse = courseRepository.findByName(course.getName());
        if (existingCourse.isPresent()) {
            LOG.warn("Course with name {} already exists.", course.getName());
            throw new IllegalArgumentException("Course with this name already exists.");
        }
        Long instructorId = course.getInstructor().getInstructorId();
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found with ID: " + instructorId));

        course.setInstructor(instructor);
        Course savedCourse = courseRepository.save(course);
        LOG.info("Course created successfully: {}", savedCourse);
        return savedCourse;
    }


    @Override
    public Course updateCourse(Course student) {
        return null;
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> getCourseByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Course> getAllCourses() {
        LOG.info("Fetching All Courses");
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourseById(Long id) {

    }

    @Override
    public long totalCourses() {
        return courseRepository.count();
    }

    @Override
    public List<Course> getCoursesByInstructorName(String instructorName) {
        return courseRepository.findByInstructorName(instructorName);
    }
}
