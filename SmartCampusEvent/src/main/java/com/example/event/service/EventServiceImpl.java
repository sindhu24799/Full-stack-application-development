package com.example.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import com.example.event.entity.Event;
import com.example.event.entity.Event.EventStatus;
import com.example.event.exception.EventNotFoundException;
import com.example.event.repository.EventRepository;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repo;

    @Override
    public Event save(Event event) {
        if (event.getStatus() == null) {
            event.setStatus(EventStatus.UPCOMING);
        }
        return repo.save(event);
    }

    @Override
    public Event update(Long id, Event updatedEvent) {
        Event existing = getById(id);
        existing.setTitle(updatedEvent.getTitle());
        existing.setDescription(updatedEvent.getDescription());
        existing.setDepartment(updatedEvent.getDepartment());
        existing.setType(updatedEvent.getType());
        existing.setDate(updatedEvent.getDate());
        existing.setCapacity(updatedEvent.getCapacity());
        existing.setVenue(updatedEvent.getVenue());
        existing.setStatus(updatedEvent.getStatus());
        return repo.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getAll() {
        return repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Event getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EventNotFoundException(id);
        }
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findByDepartment(String department) {
        return repo.findByDepartment(department);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findByType(String type) {
        return repo.findByType(type);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getUpcomingEvents() {
        return repo.findByDateAfterOrderByDateAsc(LocalDate.now().minusDays(1));
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllDepartments() {
        return repo.findAllDepartments();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllTypes() {
        return repo.findAllTypes();
    }

    @Override
    @Transactional(readOnly = true)
    public long getEventCount() {
        return repo.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long getUpcomingEventCount() {
        return repo.countByStatus(EventStatus.UPCOMING);
    }
}