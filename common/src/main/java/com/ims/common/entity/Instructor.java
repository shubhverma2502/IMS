package com.ims.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;
    @NotBlank(message = "Instructor name is mandatory")
    private String name;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Specialization is required")
    private String specialization;

    public Instructor() {
        super();
    }

    public Instructor(Long instructorId, String name, String email, String specialization) {
        this.instructorId = instructorId;
        this.name = name;
        this.email = email;
        this.specialization = specialization;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId=" + instructorId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
