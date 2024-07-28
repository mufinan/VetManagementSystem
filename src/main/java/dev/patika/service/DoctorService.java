package dev.patika.service;

import dev.patika.entity.Doctor;
import dev.patika.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(DoctorDTO doctorDTO);
    Doctor updateDoctor(Long id, Doctor doctor);
    void deleteDoctor(Long id);
    Doctor getDoctorById(Long id);
    List<Doctor> getAllDoctors();
}
