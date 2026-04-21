package com.example.event.controller;

import com.example.event.service.EventService;
import com.example.event.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("upcomingEvents", eventService.getUpcomingEvents()
                .stream().limit(4).toList());
        model.addAttribute("totalEvents", eventService.getEventCount());
        model.addAttribute("totalRegistrations", registrationService.getRegistrationCount());
        model.addAttribute("departments", eventService.getAllDepartments());
        return "home";
    }

    @GetMapping("/events")
    public String events(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String type,
            Model model) {

        if (department != null && !department.isBlank()) {
            model.addAttribute("events", eventService.findByDepartment(department));
            model.addAttribute("selectedDepartment", department);
        } else if (type != null && !type.isBlank()) {
            model.addAttribute("events", eventService.findByType(type));
            model.addAttribute("selectedType", type);
        } else {
            model.addAttribute("events", eventService.getAll());
        }

        model.addAttribute("departments", eventService.getAllDepartments());
        model.addAttribute("types", eventService.getAllTypes());
        return "events";
    }

    @GetMapping("/events/{id}")
    public String eventDetails(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.getById(id));
        model.addAttribute("registrationCount", registrationService.getRegistrationCountByEventId(id));
        return "event-details";
    }
}