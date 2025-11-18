package com.ims.common.repository;

import com.ims.common.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    Optional<Course> findByName(String name);

    long count();
    @Query("SELECT c FROM Course c WHERE LOWER(c.instructor.name) LIKE LOWER(CONCAT('%', :instructorName, '%'))")
    List<Course> findByInstructorName(@Param("instructorName") String instructorName);
}
