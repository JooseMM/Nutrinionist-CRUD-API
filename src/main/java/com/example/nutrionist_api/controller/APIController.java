package com.example.nutrionist_api.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.nutrionist_api.model.Appointment;
import com.example.nutrionist_api.service.AppointmentService;
import com.example.nutrionist_api.utils.Response;
import com.example.nutrionist_api.utils.Validation;
import jakarta.validation.Valid;

@RestController
class APIController {
    
    @Autowired
    private AppointmentService appointmentService;

    APIController() {}

    @GetMapping("/appointments")
    public ResponseEntity<Response<List<Appointment>>> getAll() {
        try {
            // try to get all the appointments
            List<Appointment> foundAppointments = appointmentService.getAll();
            // handle no appointments found
            if(foundAppointments.isEmpty()) {
                Response<List<Appointment>> response = new Response<List<Appointment>>(HttpStatus.OK.value(),
                        "No appointments found", null, null);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            // set up the response
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
    @GetMapping("/appointment/{id}")
    public ResponseEntity<Response<Appointment>> getOne(@PathVariable String id) {
        try {
            // If the id is not valid an exception will be throw
            Validation.isUUID(id);
            // try to match the appointment
            Optional<Appointment> foundAppointments = appointmentService.getOne(id);
            // handle a not match
            if(foundAppointments.isEmpty()) {
                Response<Appointment> response = new Response<Appointment>(HttpStatus.NOT_FOUND.value(),
                        "No appointment found", null, null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            // set up the response
            Response<Appointment> response = new Response<Appointment>(HttpStatus.OK.value(),
                    "Succesful query", foundAppointments.get(), null);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            Response<Appointment> response = new Response<Appointment>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred while fetching data", null, Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PostMapping("/newAppointment")
    public ResponseEntity<Response<Appointment>> createOne(@Valid @RequestBody Appointment newAppointment) {
        try {
            // If the appointment is not valid an exception will be throw
            com.example.nutrionist_api.utils.Validation.isNewAppointmentValid(newAppointment);
            // create the new Appointment
            Appointment createdAppointment = appointmentService.createAppointment(newAppointment);
            // setup the response
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
    public ResponseEntity<Response<Appointment>> updateOne(@Valid @RequestBody Appointment updatedAppointment) {
        try {
            // If the appointment is not valid an exception will be throw
            com.example.nutrionist_api.utils.Validation.isNewAppointmentValid(updatedAppointment);
            // update the old appointment
            Appointment updateResult = appointmentService.modifyAppointment(updatedAppointment);
            // setup the response
            Response<Appointment> response = new Response<Appointment>(HttpStatus.OK.value(),
                    "Data updated succesfully", updateResult, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            Response<Appointment> response = new Response<Appointment>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred while updating the appointment.", updatedAppointment,
                    Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @DeleteMapping("/deleteAppointment/{id}")
    public ResponseEntity<Response<String>> deleteById(@Valid @PathVariable String id) {
        try {
            // If the id is not valid an exception will be throw
            Validation.isUUID(id);
            // try to delete the appointment
            appointmentService.deleteById(id);
            // setup the response
            Response<String> response = new Response<String>(HttpStatus.NO_CONTENT.value(), "Data removed succesfully", id, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Response<String> response = new Response<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred while updating the appointment.", id,
                    Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
