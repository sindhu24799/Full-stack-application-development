package com.example.event.controller;

import com.example.event.entity.Event;
import com.example.event.service.EventService;
import com.example.event.service.RegistrationService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EventService eventService;

    @Autowired
    private RegistrationService registrationService;

    // Admin Dashboard
    @GetMapping({"/", ""})
    public String dashboard(Model model) {
        model.addAttribute("totalEvents", eventService.getEventCount());
        model.addAttribute("totalRegistrations", registrationService.getRegistrationCount());
        model.addAttribute("upcomingEvents", eventService.getUpcomingEventCount());
        model.addAttribute("recentEvents", eventService.getAll().stream().limit(5).toList());
        model.addAttribute("recentRegistrations", registrationService.getAll().stream().limit(5).toList());
        return "admin/dashboard";
    }

    // Manage Events list
    @GetMapping("/events")
    public String manageEvents(Model model) {
        model.addAttribute("events", eventService.getAll());
        return "admin/manage-events";
    }

    // Show Add Event form
    @GetMapping("/events/add")
    public String addEventForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("pageTitle", "Add New Event");
        return "admin/add-event";
    }

    // Show Edit Event form
    @GetMapping("/events/edit/{id}")
    public String editEventForm(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.getById(id));
        model.addAttribute("pageTitle", "Edit Event");
        return "admin/add-event";
    }

    // Save event (create or update)
    @PostMapping("/events/save")
    public String saveEvent(@Valid @ModelAttribute("event") Event event,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", event.getId() == null ? "Add New Event" : "Edit Event");
            return "admin/add-event";
        }
        if (event.getId() != null) {
            eventService.update(event.getId(), event);
            redirectAttributes.addFlashAttribute("successMessage", "Event updated successfully!");
        } else {
            eventService.save(event);
            redirectAttributes.addFlashAttribute("successMessage", "Event created successfully!");
        }
        return "redirect:/admin/events";
    }

    // Delete event
    @GetMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        eventService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Event deleted successfully!");
        return "redirect:/admin/events";
    }

    // Stats page
    @GetMapping("/stats")
    public String stats(Model model) {
        model.addAttribute("events", eventService.getAll());
        model.addAttribute("departments", eventService.getAllDepartments());
        model.addAttribute("types", eventService.getAllTypes());
        model.addAttribute("totalEvents", eventService.getEventCount());
        model.addAttribute("totalRegistrations", registrationService.getRegistrationCount());
        model.addAttribute("upcomingCount", eventService.getUpcomingEventCount());
        model.addAttribute("allRegistrations", registrationService.getAll());
        return "admin/stats";
    }
}
