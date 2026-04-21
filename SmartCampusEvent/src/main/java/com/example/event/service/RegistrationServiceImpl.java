package com.example.event.service;

import com.example.event.entity.Event;
import com.example.event.entity.Registration;
import com.example.event.exception.RegistrationException;
import com.example.event.repository.RegistrationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository repo;

    @Autowired
    private EventService eventService;

    @Override
    public Registration save(Registration registration) {
        Event event = eventService.getById(registration.getEvent().getId());

        if (event.isFull()) {
            throw new RegistrationException("Sorry, this event is already full. No seats available.");
        }

        if (repo.existsByEmailAndEventId(registration.getEmail(), event.getId())) {
            throw new RegistrationException("You have already registered for this event.");
        }

        registration.setEvent(event);
        return repo.save(registration);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Registration> getAll() {
        return repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Registration> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Registration> findByEventId(Long eventId) {
        return repo.findByEventId(eventId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getRegistrationCount() {
        return repo.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long getRegistrationCountByEventId(Long eventId) {
        return repo.countByEventId(eventId);
    }

    @Override
    public void deleteRegistration(Long id) {
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isAlreadyRegistered(String email, Long eventId) {
        return repo.existsByEmailAndEventId(email, eventId);
    }
}