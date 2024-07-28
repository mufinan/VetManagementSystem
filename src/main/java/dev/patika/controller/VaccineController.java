package dev.patika.controller;

import dev.patika.dto.VaccineWithAnimalDTO;
import dev.patika.dto.VaccineDTO;
import dev.patika.entity.Vaccine;
import dev.patika.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    private final VaccineService vaccineService;

    @Autowired
    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping("/protection-finish")
    public ResponseEntity<List<VaccineWithAnimalDTO>> getVaccinesByProtectionFinishDateBetween(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return ResponseEntity.ok(vaccineService.getVaccinesByProtectionFinishDateBetween(start, end));
    }

    @PostMapping
    public Vaccine addVaccine(@RequestBody VaccineDTO vaccineDTO) {
        return vaccineService.saveVaccine(vaccineDTO);
    }

    @GetMapping("/animal/{animalId}")
    public List<Vaccine> getVaccinesByAnimalId(@PathVariable Long animalId) {
        return vaccineService.getVaccinesByAnimalId(animalId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaccine> updateVaccine(@PathVariable Long id, @RequestBody Vaccine vaccine) {
        return ResponseEntity.ok(vaccineService.updateVaccine(id, vaccine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Long id) {
        vaccineService.deleteVaccine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccine> getVaccineById(@PathVariable Long id) {
        return ResponseEntity.ok(vaccineService.getVaccineById(id));
    }

    @GetMapping
    public ResponseEntity<List<Vaccine>> getAllVaccines() {
        return ResponseEntity.ok(vaccineService.getAllVaccines());
    }
}
