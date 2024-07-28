package dev.patika.service.impl;

import dev.patika.dto.AppointmentDTO;
import dev.patika.entity.Animal;
import dev.patika.entity.Appointment;
import dev.patika.entity.AvailableDate;
import dev.patika.entity.Doctor;
import dev.patika.exception.AppointmentConflictException;
import dev.patika.exception.DoctorNotAvailableException;
import dev.patika.repository.AnimalRepository;
import dev.patika.repository.AppointmentRepository;
import dev.patika.repository.AvailableDateRepository;
import dev.patika.repository.DoctorRepository;
import dev.patika.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AvailableDateRepository availableDateRepository;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  AvailableDateRepository availableDateRepository,
                                  DoctorRepository doctorRepository,
                                  AnimalRepository animalRepository) {
        this.appointmentRepository = appointmentRepository;
        this.availableDateRepository = availableDateRepository;
        this.doctorRepository = doctorRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public Appointment saveAppointment(AppointmentDTO appointmentDTO) {
        LocalDateTime appointmentDate = appointmentDTO.getAppointmentDate();
        Long doctorId = appointmentDTO.getDoctorId();
        Long animalId = appointmentDTO.getAnimalId();

        if (doctorId == null || animalId == null) {
            throw new RuntimeException("Doctor ID and Animal ID cannot be null");
        }

        // Doktorun mevcut randevularını kontrol et
        Optional<Appointment> existingAppointment = appointmentRepository
                .findByDoctorIdAndAppointmentDate(doctorId, appointmentDate);

        if (existingAppointment.isPresent()) {
            throw new AppointmentConflictException("Girilen saatte başka bir randevu mevcuttur.");
        }

        // Doktorun müsait günlerini kontrol et
        LocalDate appointmentDay = appointmentDate.toLocalDate();
        System.out.println("Checking availability for doctor ID: " + doctorId + " on date: " + appointmentDay);

        Optional<AvailableDate> availableDate = availableDateRepository
                .findByDoctorIdAndAvailableDate(doctorId, appointmentDay);

        if (!availableDate.isPresent()) {
            System.out.println("Doctor not available on date: " + appointmentDay);
            throw new DoctorNotAvailableException("Doktor bu tarihte çalışmamaktadır!");
        } else {
            System.out.println("Doctor is available on date: " + appointmentDay);
        }

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDate);
        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId, startDate, endDate);
    }

    @Override
    public List<Appointment> getAppointmentsByAnimalAndDateRange(Long animalId, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findByAnimalIdAndAppointmentDateBetween(animalId, startDate, endDate);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
        existingAppointment.setAnimal(appointment.getAnimal());
        existingAppointment.setDoctor(appointment.getDoctor());
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointmentRepository.delete(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
