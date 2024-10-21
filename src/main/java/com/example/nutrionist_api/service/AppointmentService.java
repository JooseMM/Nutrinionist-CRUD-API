package com.example.nutrionist_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nutrionist_api.enums.Gender;
import com.example.nutrionist_api.model.Appointment;

@Service
public class AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment createAppointment(
        String clientName,
        int clientAge,
        Gender clientSex,
        String clientEmail,
        String clientGoal,
        String clientDiagnosis
        ) {

            Appointment appointment = new Appointment();

            appointment.setClientName(clientName);
            appointment.setClientAge(clientAge);
            appointment.setClientSex(clientSex);
            appointment.setClientEmail(clientEmail);
            appointment.setClientGoal(clientGoal);
            appointment.setClientDiagnosis(clientDiagnosis);

            return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments;
    }

    
}
