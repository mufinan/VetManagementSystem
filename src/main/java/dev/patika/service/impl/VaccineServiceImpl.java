package dev.patika.service.impl;

import dev.patika.dto.VaccineDTO;
import dev.patika.dto.VaccineWithAnimalDTO;
import dev.patika.entity.Animal;
import dev.patika.entity.Vaccine;
import dev.patika.exception.AnimalNotFoundException;
import dev.patika.exception.ExistingVaccineException;
import dev.patika.repository.AnimalRepository;
import dev.patika.repository.VaccineRepository;
import dev.patika.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public VaccineServiceImpl(VaccineRepository vaccineRepository, AnimalRepository animalRepository) {
        this.vaccineRepository = vaccineRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public Vaccine saveVaccine(VaccineDTO vaccineDTO) {
        // Koruyuculuk bitiş tarihi kontrolü
        if (vaccineRepository.existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(
                vaccineDTO.getAnimalId(), vaccineDTO.getName(), vaccineDTO.getCode(), LocalDate.now())) {
            throw new ExistingVaccineException("Hayvan için aynı tip aşı hala geçerli.");
        }

        Vaccine vaccine = new Vaccine();
        vaccine.setName(vaccineDTO.getName());
        vaccine.setCode(vaccineDTO.getCode());
        vaccine.setProtectionStartDate(vaccineDTO.getProtectionStartDate());
        vaccine.setProtectionFinishDate(vaccineDTO.getProtectionFinishDate());

        Animal animal = animalRepository.findById(vaccineDTO.getAnimalId())
                .orElseThrow(() -> new AnimalNotFoundException("Animal not found"));

        vaccine.setAnimal(animal);

        return vaccineRepository.save(vaccine);
    }

    @Override
    public List<Vaccine> getVaccinesByAnimalId(Long animalId) {
        return vaccineRepository.findByAnimalId(animalId);
    }

    @Override
    public List<VaccineWithAnimalDTO> getVaccinesByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> vaccines = vaccineRepository.findByProtectionFinishDateBetween(startDate, endDate);

        return vaccines.stream().map(vaccine -> {
            VaccineWithAnimalDTO dto = new VaccineWithAnimalDTO();
            dto.setVaccineId(vaccine.getId());
            dto.setVaccineName(vaccine.getName());
            dto.setVaccineCode(vaccine.getCode());
            dto.setProtectionStartDate(vaccine.getProtectionStartDate());
            dto.setProtectionFinishDate(vaccine.getProtectionFinishDate());
            dto.setAnimalId(vaccine.getAnimal().getId());
            dto.setAnimalName(vaccine.getAnimal().getName());
            dto.setAnimalSpecies(vaccine.getAnimal().getSpecies());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Vaccine updateVaccine(Long id, Vaccine vaccine) {
        Vaccine existingVaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccine not found"));
        existingVaccine.setName(vaccine.getName());
        existingVaccine.setCode(vaccine.getCode());
        existingVaccine.setProtectionStartDate(vaccine.getProtectionStartDate());
        existingVaccine.setProtectionFinishDate(vaccine.getProtectionFinishDate());
        return vaccineRepository.save(existingVaccine);
    }

    @Override
    public void deleteVaccine(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccine not found"));
        vaccineRepository.delete(vaccine);
    }

    @Override
    public Vaccine getVaccineById(Long id) {
        return vaccineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccine not found"));
    }

    @Override
    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }
}
