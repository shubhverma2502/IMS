package com.ims.auth.controller;

import com.ims.common.entity.Instructor;
import com.ims.instructor.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InctructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/instructor/new")
    public String showAddInstructorForm(Model model){
        Instructor instructor = new Instructor();
        model.addAttribute("instructor",instructor);
        return "instructors/add-instructor-form";

    }

    @PostMapping("/instructor/add")
    public String addInstructor(@ModelAttribute("instructor") @Valid Instructor instructor,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "add-instructor";
        }
        try{
            instructorService.createInstructor(instructor);
            redirectAttributes.addFlashAttribute("successMessage", "Instructor added successfully!");
            return "redirect:/instructors";
        }catch (IllegalArgumentException e){
            result.rejectValue("email", "error.instructor", e.getMessage());
            return "instructors/add-instructor";
        }
    }
}
