package com.example.event.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(nullable = false)
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    @Column(length = 1000)
    private String description;

    @NotBlank(message = "Department is required")
    @Column(nullable = false)
    private String department;

    @NotBlank(message = "Event type is required")
    @Column(nullable = false)
    private String type;

    @NotNull(message = "Event date is required")
    @Column(nullable = false)
    private LocalDate date;

    @Min(value = 1, message = "Capacity must be at least 1")
    @Column(nullable = false)
    private int capacity;

    @NotBlank(message = "Venue is required")
    @Column(nullable = false)
    private String venue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status = EventStatus.UPCOMING;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrations = new ArrayList<>();

    public enum EventStatus {
        UPCOMING, ONGOING, COMPLETED, CANCELLED
    }

    public Event() {
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    
    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
    
    public EventStatus getStatus() { return status; }
    public void setStatus(EventStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public List<Registration> getRegistrations() { return registrations; }
    public void setRegistrations(List<Registration> registrations) { this.registrations = registrations; }

    public int getAvailableSeats() {
        return capacity - (registrations != null ? registrations.size() : 0);
    }

    public boolean isFull() {
        return getAvailableSeats() <= 0;
    }

    public static EventBuilder builder() {
        return new EventBuilder();
    }

    public static class EventBuilder {
        private Event event = new Event();

        public EventBuilder id(Long id) { event.setId(id); return this; }
        public EventBuilder title(String title) { event.setTitle(title); return this; }
        public EventBuilder description(String description) { event.setDescription(description); return this; }
        public EventBuilder department(String department) { event.setDepartment(department); return this; }
        public EventBuilder type(String type) { event.setType(type); return this; }
        public EventBuilder date(LocalDate date) { event.setDate(date); return this; }
        public EventBuilder capacity(int capacity) { event.setCapacity(capacity); return this; }
        public EventBuilder venue(String venue) { event.setVenue(venue); return this; }
        public EventBuilder status(EventStatus status) { event.setStatus(status); return this; }
        public EventBuilder createdAt(LocalDateTime createdAt) { event.setCreatedAt(createdAt); return this; }
        
        public Event build() { return event; }
    }
}