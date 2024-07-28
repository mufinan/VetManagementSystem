package dev.patika.repository;

import dev.patika.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findByAnimalId(Long animalId);
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
    boolean existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(Long animalId, String name, String code, LocalDate currentDate);
}
