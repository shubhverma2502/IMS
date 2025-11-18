package com.ims.common.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    @NotBlank(message = "Course name is mandatory")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "Duration is required")
    private String duration;
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public Course() {
        super();
    }

    public Course(Long courseId, String name, String description, String duration, Instructor instructor) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.instructor = instructor;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration='" + duration + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}
