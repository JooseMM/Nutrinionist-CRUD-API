package com.example.nutrionist_api.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.nutrionist_api.model.Appointment;
import com.example.nutrionist_api.service.AppointmentService;
import com.example.nutrionist_api.utils.Response;

import jakarta.validation.Valid;

@RestController
class APIController {
    
    @Autowired
    private AppointmentService appointmentService;

    APIController() {}

    @GetMapping("/appointments")
    public ResponseEntity<Response<List<Appointment>>> getAll() {
        try {
            List<Appointment> foundAppointments = appointmentService.getAll();
            if(foundAppointments.isEmpty()) {
                Response<List<Appointment>> response = new Response<List<Appointment>>(HttpStatus.OK.value(),
                        "No appointments found", null, null);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            Response<List<Appointment>> response = new Response<List<Appointment>>(HttpStatus.OK.value(),
                    "Succesful query", foundAppointments, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            Response<List<Appointment>> response = new Response<List<Appointment>>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred while fetching data", null, Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PostMapping("/newAppointment")
    public ResponseEntity<Response<Appointment>> createOne(@Valid @RequestBody Appointment newAppointment) {
        try {
            // if this is not a valid appointment it will trhow an IllegalArgumentException;
            com.example.nutrionist_api.utils.Validation.isNewAppointmentValid(newAppointment);
            Appointment createdAppointment = appointmentService.createAppointment(newAppointment);
            Response<Appointment> response = new Response<Appointment>(HttpStatus.CREATED.value(),
                    "Appointment Created", createdAppointment, null);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Response<Appointment> response = new Response<Appointment>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred while creating the appointment.", newAppointment,
                    Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PutMapping("/updateAppointment")
    Appointment updateOne(@Valid @RequestBody Appointment newAppointment) {
        // if this is not a valid appointment it will trhow an IllegalArgumentException;
        com.example.nutrionist_api.utils.Validation.isNewAppointmentValid(newAppointment);
        return appointmentService.modifyAppointment(newAppointment);
    }
}
