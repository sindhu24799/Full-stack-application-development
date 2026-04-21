package com.example.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.event.entity.Event;
import com.example.event.entity.Event.EventStatus;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDepartment(String department);

    List<Event> findByDate(LocalDate date);

    List<Event> findByType(String type);

    List<Event> findByStatus(EventStatus status);

    List<Event> findByDateAfterOrderByDateAsc(LocalDate date);

    @Query("SELECT DISTINCT e.department FROM Event e ORDER BY e.department")
    List<String> findAllDepartments();

    @Query("SELECT DISTINCT e.type FROM Event e ORDER BY e.type")
    List<String> findAllTypes();

    long countByStatus(EventStatus status);
}