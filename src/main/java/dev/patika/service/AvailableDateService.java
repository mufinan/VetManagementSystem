package dev.patika.service;

import dev.patika.entity.AvailableDate;
import dev.patika.dto.AvailableDateDTO;

import java.util.List;

public interface AvailableDateService {
    AvailableDate saveAvailableDate(AvailableDateDTO availableDateDTO);
    AvailableDate updateAvailableDate(Long id, AvailableDate availableDate);
    void deleteAvailableDate(Long id);
    AvailableDate getAvailableDateById(Long id);
    List<AvailableDate> getAllAvailableDates();
}
