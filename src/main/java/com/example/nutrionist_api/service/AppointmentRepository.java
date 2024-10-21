package com.example.nutrionist_api.service;

import com.example.nutrionist_api.model.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
