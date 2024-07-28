package dev.patika.controller;

import dev.patika.dto.AvailableDateDTO;
import dev.patika.entity.AvailableDate;
import dev.patika.service.AvailableDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/available-dates")
public class AvailableDateController {

    private final AvailableDateService availableDateService;

    @Autowired
    public AvailableDateController(AvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @PostMapping
    public AvailableDate addAvailableDate(@RequestBody AvailableDateDTO availableDateDTO) {
        return availableDateService.saveAvailableDate(availableDateDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailableDate> updateAvailableDate(@PathVariable Long id, @RequestBody AvailableDate availableDate) {
        return ResponseEntity.ok(availableDateService.updateAvailableDate(id, availableDate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailableDate(@PathVariable Long id) {
        availableDateService.deleteAvailableDate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailableDate> getAvailableDateById(@PathVariable Long id) {
        return ResponseEntity.ok(availableDateService.getAvailableDateById(id));
    }

    @GetMapping
    public ResponseEntity<List<AvailableDate>> getAllAvailableDates() {
        return ResponseEntity.ok(availableDateService.getAllAvailableDates());
    }
}
