package dev.patika.service.impl;

import dev.patika.dto.DoctorDTO;
import dev.patika.entity.Doctor;
import dev.patika.repository.DoctorRepository;
import dev.patika.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor saveDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setPhone(doctorDTO.getPhone());
        doctor.setMail(doctorDTO.getMail());
        doctor.setAddress(doctorDTO.getAddress());
        doctor.setCity(doctorDTO.getCity());

        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        existingDoctor.setName(doctor.getName());
        existingDoctor.setPhone(doctor.getPhone());
        existingDoctor.setMail(doctor.getMail());
        existingDoctor.setAddress(doctor.getAddress());
        existingDoctor.setCity(doctor.getCity());
        return doctorRepository.save(existingDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctorRepository.delete(doctor);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
