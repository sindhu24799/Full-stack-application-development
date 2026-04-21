package com.example.event.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException(Long id) {
        super("Event not found with ID: " + id);
    }
}
