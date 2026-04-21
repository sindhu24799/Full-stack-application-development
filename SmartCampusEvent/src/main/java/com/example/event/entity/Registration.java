package com.example.event.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registrations", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email", "event_id"})
})
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Student name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String studentName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    @Column(nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(updatable = false)
    private LocalDateTime registeredAt = LocalDateTime.now();

    public Registration() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }

    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }

    public static RegistrationBuilder builder() {
        return new RegistrationBuilder();
    }

    public static class RegistrationBuilder {
        private Registration registration = new Registration();

        public RegistrationBuilder id(Long id) { registration.setId(id); return this; }
        public RegistrationBuilder studentName(String studentName) { registration.setStudentName(studentName); return this; }
        public RegistrationBuilder email(String email) { registration.setEmail(email); return this; }
        public RegistrationBuilder phone(String phone) { registration.setPhone(phone); return this; }
        public RegistrationBuilder event(Event event) { registration.setEvent(event); return this; }
        public RegistrationBuilder registeredAt(LocalDateTime registeredAt) { registration.setRegisteredAt(registeredAt); return this; }

        public Registration build() { return registration; }
    }
}