package com.example.event.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Full name is required")
    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.STUDENT;

    private boolean enabled = true;

    public enum Role {
        ADMIN, STUDENT
    }

    public User() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private User user = new User();
        
        public UserBuilder id(Long id) { user.setId(id); return this; }
        public UserBuilder username(String username) { user.setUsername(username); return this; }
        public UserBuilder password(String password) { user.setPassword(password); return this; }
        public UserBuilder email(String email) { user.setEmail(email); return this; }
        public UserBuilder fullName(String fullName) { user.setFullName(fullName); return this; }
        public UserBuilder role(Role role) { user.setRole(role); return this; }
        public UserBuilder enabled(boolean enabled) { user.setEnabled(enabled); return this; }
        
        public User build() { return user; }
    }
}
