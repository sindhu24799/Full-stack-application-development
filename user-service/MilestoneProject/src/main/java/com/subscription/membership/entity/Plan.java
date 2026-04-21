package com.subscription.membership.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Plan {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName;
    private double price;
    private String features;
	

}
