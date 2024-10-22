package com.example.nutrionist_api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nutrionist_api.model.Appointment;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment newAppointment) {
            return appointmentRepository.save(newAppointment);
    }

    public List<Appointment> getAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments;
    }

    public Optional<Appointment> getOne(String id) {
        return appointmentRepository.findById(id);
    }

    public Appointment modifyAppointment(Appointment newAppointment) {
        String matchId = newAppointment.getId();
        Appointment existingAppointment = appointmentRepository.findById(matchId)
            .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        updateAppointmentFields(existingAppointment, newAppointment);
        return appointmentRepository.save(existingAppointment);

    }

    private static void updateAppointmentFields(Appointment existingAppointment, Appointment newAppointment) {
        existingAppointment.setClientName(newAppointment.getClientName());
        existingAppointment.setClientAge(newAppointment.getClientAge());
        existingAppointment.setClientSex(newAppointment.getClientSex());
        existingAppointment.setClientEmail(newAppointment.getClientEmail());
        existingAppointment.setClientGoal(newAppointment.getClientGoal());
        existingAppointment.setClientDiagnosis(newAppointment.getClientDiagnosis());
        existingAppointment.setDateTime(newAppointment.getDateTime());
    }
    
}
