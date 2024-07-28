package dev.patika.service;

import dev.patika.dto.AppointmentDTO;
import dev.patika.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    Appointment saveAppointment(AppointmentDTO appointmentDTO);
    List<Appointment> getAppointmentsByDoctorAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> getAppointmentsByAnimalAndDateRange(Long animalId, LocalDateTime startDate, LocalDateTime endDate);
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
    Appointment getAppointmentById(Long id);
    List<Appointment> getAllAppointments();
}
