package com.example.event.config;

import com.example.event.entity.Event;
import com.example.event.entity.Event.EventStatus;
import com.example.event.entity.User;
import com.example.event.entity.User.Role;
import com.example.event.repository.EventRepository;
import com.example.event.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create admin user if not exists
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .email("admin@campus.edu")
                    .fullName("Campus Administrator")
                    .role(Role.ADMIN)
                    .enabled(true)
                    .build();
            userRepository.save(admin);
            System.out.println(">>> Admin user created: admin / admin123");
        }

        // Create sample student
        if (!userRepository.existsByUsername("student")) {
            User student = User.builder()
                    .username("student")
                    .password(passwordEncoder.encode("student123"))
                    .email("student@campus.edu")
                    .fullName("Demo Student")
                    .role(Role.STUDENT)
                    .enabled(true)
                    .build();
            userRepository.save(student);
            System.out.println(">>> Student user created: student / student123");
        }

        // Create sample events if none exist
        if (eventRepository.count() == 0) {
            eventRepository.save(Event.builder()
                    .title("Annual Tech Fest 2025")
                    .description("Join us for the biggest technology festival on campus featuring hackathons, tech talks, and innovation showcases from industry leaders.")
                    .department("Computer Science")
                    .type("Festival")
                    .date(LocalDate.now().plusDays(15))
                    .capacity(200)
                    .venue("Main Auditorium")
                    .status(EventStatus.UPCOMING)
                    .createdAt(LocalDateTime.now())
                    .build());

            eventRepository.save(Event.builder()
                    .title("AI & Machine Learning Workshop")
                    .description("Hands-on workshop covering neural networks, deep learning frameworks, and real-world AI applications with industry experts.")
                    .department("Computer Science")
                    .type("Workshop")
                    .date(LocalDate.now().plusDays(7))
                    .capacity(50)
                    .venue("CS Lab 301")
                    .status(EventStatus.UPCOMING)
                    .createdAt(LocalDateTime.now())
                    .build());

            eventRepository.save(Event.builder()
                    .title("Entrepreneurship Summit")
                    .description("Meet successful entrepreneurs, learn about startup ecosystems, and pitch your ideas to potential investors.")
                    .department("Business Administration")
                    .type("Seminar")
                    .date(LocalDate.now().plusDays(10))
                    .capacity(150)
                    .venue("Business School Hall")
                    .status(EventStatus.UPCOMING)
                    .createdAt(LocalDateTime.now())
                    .build());

            eventRepository.save(Event.builder()
                    .title("Inter-College Sports Meet")
                    .description("Annual inter-college sports competition featuring athletics, cricket, football, basketball and more.")
                    .department("Physical Education")
                    .type("Sports")
                    .date(LocalDate.now().plusDays(20))
                    .capacity(500)
                    .venue("University Stadium")
                    .status(EventStatus.UPCOMING)
                    .createdAt(LocalDateTime.now())
                    .build());

            eventRepository.save(Event.builder()
                    .title("Cultural Night - Harmony")
                    .description("An evening of music, dance, drama and art celebrating the diverse cultural heritage of our campus community.")
                    .department("Arts & Culture")
                    .type("Cultural")
                    .date(LocalDate.now().plusDays(5))
                    .capacity(300)
                    .venue("Open Air Theatre")
                    .status(EventStatus.UPCOMING)
                    .createdAt(LocalDateTime.now())
                    .build());

            eventRepository.save(Event.builder()
                    .title("Research Paper Presentation")
                    .description("Present and discuss cutting-edge research papers across multiple disciplines with faculty and peers.")
                    .department("Research & Development")
                    .type("Seminar")
                    .date(LocalDate.now().plusDays(12))
                    .capacity(80)
                    .venue("Conference Room A")
                    .status(EventStatus.UPCOMING)
                    .createdAt(LocalDateTime.now())
                    .build());

            System.out.println(">>> Sample events created successfully");
        }
    }
}
