package dev.patika.service;

import dev.patika.dto.VaccineDTO;
import dev.patika.dto.VaccineWithAnimalDTO;
import dev.patika.entity.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface VaccineService {
    Vaccine saveVaccine(VaccineDTO vaccineDTO);
    List<Vaccine> getVaccinesByAnimalId(Long animalId);
    List<VaccineWithAnimalDTO> getVaccinesByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
    //List<Vaccine> getVaccinesByProtectionFinishDateRange(LocalDate startDate, LocalDate endDate);
    Vaccine updateVaccine(Long id, Vaccine vaccine);
    void deleteVaccine(Long id);
    Vaccine getVaccineById(Long id);
    List<Vaccine> getAllVaccines();
}
