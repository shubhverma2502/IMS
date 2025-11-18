package com.ims.instructor.service;

import com.ims.common.entity.Instructor;
import com.ims.common.repository.InstructorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InstructorServiceImpl implements InstructorService{
    private static final Logger LOG = LoggerFactory.getLogger(InstructorServiceImpl.class);
    @Autowired
    InstructorRepository instructorRepository;
    @Override
    public Instructor createInstructor(Instructor instructor) {
        var existingInstructor = instructorRepository.findByEmail(instructor.getEmail());
        if (existingInstructor.isPresent()) {
            LOG.warn("Instructor with email {} already exists.", instructor.getEmail());
            throw new IllegalArgumentException("Instructor with this email already exists.");
        }
        Instructor savedInstructor = instructorRepository.save(instructor);
        LOG.info("Student created successfully: {}", savedInstructor);
        return savedInstructor;
    }

    @Override
    public Instructor updateInstructor(Instructor instructor) {
        if(instructor.getInstructorId() == null || !instructorRepository.existsById(instructor.getInstructorId())){
            LOG.warn("Instructor with ID {} does not exist", instructor.getInstructorId());
            throw new IllegalArgumentException("Instructor does not exist");
        }

        Instructor existingInstructor = instructorRepository.findById(instructor.getInstructorId())
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));

        existingInstructor.setName(instructor.getName());
        existingInstructor.setEmail(instructor.getEmail());
        existingInstructor.setSpecialization(instructor.getSpecialization());

        Instructor updatedInstructor = instructorRepository.save(existingInstructor);
        LOG.info("Instructor updated: {}", updatedInstructor);
        return updatedInstructor;
    }

    @Override
    public Optional<Instructor> getInstructorById(Long id) {
        LOG.info("Fetching instructor by ID: {}",id);
        return instructorRepository.findById(id);
    }

    @Override
    public Optional<Instructor> getInstructorByName(String name) {
        LOG.info("Fetching Instructor by Name: {}", name);
        return instructorRepository.findByName(name);
    }

    @Override
    public List<Instructor> getAllInstructor() {
        LOG.info("Fetching All Instructor");
        return instructorRepository.findAll();
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!instructorRepository.existsById(id)){
            LOG.warn("Instructor with ID {} not found for deletion.",id);
            throw new IllegalArgumentException("Instructor not found.");
        }
        instructorRepository.deleteById(id);
        LOG.info("Instructor with ID {} deleted", id);

    }

    @Override
    public long totalInstructor() {
        return instructorRepository.count();
    }
}
