package dev.patika.service;

import dev.patika.dto.AnimalDTO;
import dev.patika.entity.Animal;

import java.util.List;

public interface AnimalService {
    Animal updateAnimal(Long id, Animal animal);
    void deleteAnimal(Long id);
    Animal getAnimalById(Long id);
    List<Animal> getAllAnimals();
    List<Animal> findAnimalsByName(String name);
    Animal saveAnimal(AnimalDTO animalDTO);
}
