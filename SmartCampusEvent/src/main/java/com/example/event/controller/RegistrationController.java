package com.example.event.controller;

import com.example.event.entity.Registration;
import com.example.event.service.RegistrationService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute Registration registration,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please fill in all required fields correctly.");
            return "redirect:/events/" + registration.getEvent().getId();
        }
        try {
            registrationService.save(registration);
            redirectAttributes.addFlashAttribute("successMessage",
                    "You have successfully registered for the event!");
            return "redirect:/my-events?email=" + registration.getEmail();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/events/" + registration.getEvent().getId();
        }
    }

    @GetMapping("/my-events")
    public String myEvents(@RequestParam(required = false) String email,
                           org.springframework.ui.Model model) {
        if (email != null && !email.isBlank()) {
            model.addAttribute("registrations", registrationService.findByEmail(email));
            model.addAttribute("email", email);
        } else {
            model.addAttribute("registrations", java.util.Collections.emptyList());
        }
        return "my-events";
    }
}