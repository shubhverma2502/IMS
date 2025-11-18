package com.ims.main;

import com.ims.common.repository.CourseRepository;
import com.ims.common.repository.InstructorRepository;
import com.ims.common.repository.StudentRepository;
import com.ims.common.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.ims.common",
        "com.ims.auth",
        "com.ims.student",
        "com.ims.course",
        "com.ims.instructor"
})
@EntityScan(basePackages = {
        "com.ims.common",
        "com.ims.auth",
        "com.ims.student",
        "com.ims.course",
        "com.ims.instructor"

})
@EnableJpaRepositories(basePackageClasses = {
        UserRepository.class,
        StudentRepository.class,
        CourseRepository.class,
        InstructorRepository.class

}) 
public class Main extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}