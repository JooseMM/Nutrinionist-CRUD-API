package com.example.nutrionist_api.controller;

import com.example.nutrionist_api.service.AppointmentRepository;
import com.example.nutrionist_api.model.Appointment;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class APIController {
    private final AppointmentRepository repository;

    APIController(AppointmentRepository repository) {
	this.repository = repository;
    }

    @GetMapping("/appointments")
    List<Appointment> getAll() {
	return repository.findAll();
    }
    @PostMapping("/newAppointment")
    String createOne(@RequestBody Appointment newAppointment) {
	return newAppointment.toString();
    }
}
