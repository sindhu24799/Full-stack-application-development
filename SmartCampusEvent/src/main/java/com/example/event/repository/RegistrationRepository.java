package com.example.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.event.entity.Event;
import com.example.event.entity.Registration;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findByEmail(String email);

    List<Registration> findByEvent(Event event);

    List<Registration> findByEventId(Long eventId);

    long countByEvent(Event event);

    long countByEventId(Long eventId);

    boolean existsByEmailAndEventId(String email, Long eventId);
}