package dev.patika.controller;

import dev.patika.dto.AnimalAppointmentFilter;
import dev.patika.dto.AppointmentDTO;
import dev.patika.dto.DoctorAppointmentFilter;
import dev.patika.entity.Appointment;
import dev.patika.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public Appointment addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.saveAppointment(appointmentDTO);
    }

    @PostMapping("/doctor")
    public List<Appointment> getAppointmentsByDoctorAndDateRange(@RequestBody DoctorAppointmentFilter filter) {
        System.out.println("Received filter: " + filter);
        return appointmentService.getAppointmentsByDoctorAndDateRange(filter.getDoctorId(), filter.getStartDate(), filter.getEndDate());
    }

    @PostMapping("/animal")
    public List<Appointment> getAppointmentsByAnimalAndDateRange(@RequestBody AnimalAppointmentFilter filter) {
        System.out.println("Received filter: " + filter);
        return appointmentService.getAppointmentsByAnimalAndDateRange(filter.getAnimalId(), filter.getStartDate(), filter.getEndDate());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
}
