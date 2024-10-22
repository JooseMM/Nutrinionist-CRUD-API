package com.example.nutrionist_api.controller;

import com.example.nutrionist_api.service.AppointmentService;

import jakarta.validation.Valid;

import com.example.nutrionist_api.model.Appointment;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Validation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PathVariable;

@RestController
class APIController {
    
    @Autowired
    private AppointmentService appointmentService;

    APIController() {}

    @GetMapping("/appointments")
    List<Appointment> getAll() {
	return appointmentService.getAll();
    }
    @PostMapping("/newAppointment")
    Appointment createOne(@Valid @RequestBody Appointment newAppointment) {
        // if this is not a valid appointment it will trhow an IllegalArgumentException;
        com.example.nutrionist_api.utils.Validation.isNewAppointmentValid(newAppointment);
        return appointmentService.createAppointment(newAppointment);
    }
    @PutMapping("/updateAppointment")
    Appointment updateOne(@Valid @RequestBody Appointment newAppointment) {
        // if this is not a valid appointment it will trhow an IllegalArgumentException;
        com.example.nutrionist_api.utils.Validation.isNewAppointmentValid(newAppointment);
        return appointmentService.modifyAppointment(newAppointment);
    }
}
