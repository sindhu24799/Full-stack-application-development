package com.example.event.service;

import java.util.List;
import com.example.event.entity.Event;

public interface EventService {

    Event save(Event event);

    Event update(Long id, Event event);

    List<Event> getAll();

    Event getById(Long id);

    void delete(Long id);

    List<Event> findByDepartment(String department);

    List<Event> findByType(String type);

    List<Event> getUpcomingEvents();

    List<String> getAllDepartments();

    List<String> getAllTypes();

    long getEventCount();

    long getUpcomingEventCount();
}