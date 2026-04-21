package com.example.event.controller;

import com.example.event.entity.User;
import com.example.event.entity.User.Role;
import com.example.event.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error,
                            @RequestParam(required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password. Please try again.");
        }
        if (logout != null) {
            model.addAttribute("successMessage", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/register-user")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (result.hasErrors()) {
            return "signup";
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("errorMessage", "Username is already taken. Please choose another.");
            return "signup";
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("errorMessage", "Email is already registered. Please log in.");
            return "signup";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.STUDENT);
        user.setEnabled(true);
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("successMessage",
                "Account created successfully! Please log in.");
        return "redirect:/login";
    }
}
