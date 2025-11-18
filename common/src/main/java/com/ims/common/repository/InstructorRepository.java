package com.ims.common.repository;

import com.ims.common.entity.Instructor;
import com.ims.common.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    Optional<Instructor> findByEmail(String email);
    Optional<Instructor> findByName(String name);

    long count();
}
