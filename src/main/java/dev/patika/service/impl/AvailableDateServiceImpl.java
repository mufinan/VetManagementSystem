package dev.patika.service.impl;

import dev.patika.dto.AvailableDateDTO;
import dev.patika.entity.AvailableDate;
import dev.patika.entity.Doctor;
import dev.patika.repository.AvailableDateRepository;
import dev.patika.repository.DoctorRepository;
import dev.patika.service.AvailableDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableDateServiceImpl implements AvailableDateService {

    private final AvailableDateRepository availableDateRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public AvailableDateServiceImpl(AvailableDateRepository availableDateRepository, DoctorRepository doctorRepository) {
        this.availableDateRepository = availableDateRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public AvailableDate saveAvailableDate(AvailableDateDTO availableDateDTO) {
        AvailableDate availableDate = new AvailableDate();
        availableDate.setAvailableDate(availableDateDTO.getAvailableDate());

        Doctor doctor = doctorRepository.findById(availableDateDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        availableDate.setDoctor(doctor);

        return availableDateRepository.save(availableDate);
    }

    @Override
    public AvailableDate updateAvailableDate(Long id, AvailableDate availableDate) {
        AvailableDate existingAvailableDate = availableDateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Available date not found"));
        existingAvailableDate.setAvailableDate(availableDate.getAvailableDate());
        return availableDateRepository.save(existingAvailableDate);
    }

    @Override
    public void deleteAvailableDate(Long id) {
        AvailableDate availableDate = availableDateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Available date not found"));
        availableDateRepository.delete(availableDate);
    }

    @Override
    public AvailableDate getAvailableDateById(Long id) {
        return availableDateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Available date not found"));
    }

    @Override
    public List<AvailableDate> getAllAvailableDates() {
        return availableDateRepository.findAll();
    }
}
