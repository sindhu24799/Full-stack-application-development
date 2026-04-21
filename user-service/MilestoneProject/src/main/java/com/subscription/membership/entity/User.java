package com.subscription.membership.entity;

import jakarta.persistence.*;
<<<<<<< HEAD




@Entity
//@Data
=======
import lombok.*;


@Entity
@Data
>>>>>>> 5ef612e (Initial commit)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
<<<<<<< HEAD
    
    public User() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
=======
>>>>>>> 5ef612e (Initial commit)

}
