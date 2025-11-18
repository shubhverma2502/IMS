package com.ims.student.service;

import com.ims.common.entity.Course;
import com.ims.common.entity.Student;
import com.ims.common.repository.CourseRepository;
import com.ims.common.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Student createStudent(Student student) {
        var existingStudent = studentRepository.findByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            LOG.warn("Student with email {} already exists.", student.getEmail());
            throw new IllegalArgumentException("Student with this email already exists.");
        }
        Long courseId = student.getCourse().getCourseId();
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Course ID: " + courseId));

        student.setCourse(course);

        Student savedStudent = studentRepository.save(student);
        LOG.info("Student created successfully: {}", savedStudent);
        return savedStudent;
    }


    @Override
    public Student updateStudent(Student student) {
        if (student.getStudentId() == null || !studentRepository.existsById(student.getStudentId())) {
            LOG.warn("Student with ID {} does not exist.", student.getStudentId());
            throw new IllegalArgumentException("Student does not exist.");
        }

        Student existingStudent = studentRepository.findById(student.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        if (student.getCourse() != null && student.getCourse().getCourseId() != null) {
            Course course = courseRepository.findById(student.getCourse().getCourseId())
                    .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + student.getCourse().getCourseId()));
            existingStudent.setCourse(course);
        }
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhone(student.getPhone());
        existingStudent.setEnrollmentDate(student.getEnrollmentDate());

        Student updatedStudent = studentRepository.save(existingStudent);
        LOG.info("Student updated: {}", updatedStudent);
        return updatedStudent;
    }


    @Override
    public Optional<Student> getStudentById(Long id) {
        LOG.info("Fetching student by ID: {}", id);
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> getStudentByName(String name) {
        LOG.info("Fetching student by name: {}", name);
        return studentRepository.findByName(name);
    }

    @Override
    public List<Student> getAllStudents() {

        LOG.info("Fetching all students");
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            LOG.warn("Student with ID {} not found for deletion.", id);
            throw new IllegalArgumentException("Student not found.");
        }
        studentRepository.deleteById(id);
        LOG.info("Student with ID {} deleted.", id);
    }

    @Override
    public long getTotalStudents() {
        return studentRepository.count();
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        return studentRepository.searchByNameOrCourse(keyword);
    }
}
