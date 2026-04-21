package com.example.event.service;

import com.example.event.entity.Registration;
import java.util.List;

public interface RegistrationService {

    Registration save(Registration registration);

    List<Registration> getAll();

    List<Registration> findByEmail(String email);

    List<Registration> findByEventId(Long eventId);

    long getRegistrationCount();

    long getRegistrationCountByEventId(Long eventId);

    void deleteRegistration(Long id);

    boolean isAlreadyRegistered(String email, Long eventId);
}