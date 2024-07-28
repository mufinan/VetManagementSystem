package dev.patika.service.impl;

import dev.patika.dto.AnimalDTO;
import dev.patika.entity.Animal;
import dev.patika.entity.Customer;
import dev.patika.repository.AnimalRepository;
import dev.patika.repository.CustomerRepository;
import dev.patika.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, CustomerRepository customerRepository) {
        this.animalRepository = animalRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Animal saveAnimal(AnimalDTO animalDTO) {
        Animal animal = new Animal();
        animal.setName(animalDTO.getName());
        animal.setSpecies(animalDTO.getSpecies());
        animal.setBreed(animalDTO.getBreed());
        animal.setGender(animalDTO.getGender());
        animal.setColour(animalDTO.getColour());
        animal.setDateOfBirth(animalDTO.getDateOfBirth());

        Customer customer = customerRepository.findById(animalDTO.getCustomer_id())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        animal.setCustomer(customer);

        return animalRepository.save(animal);
    }

    @Override
    public Animal updateAnimal(Long id, Animal animal) {
        Animal existingAnimal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        existingAnimal.setName(animal.getName());
        existingAnimal.setSpecies(animal.getSpecies());
        existingAnimal.setBreed(animal.getBreed());
        existingAnimal.setGender(animal.getGender());
        existingAnimal.setColour(animal.getColour());
        existingAnimal.setDateOfBirth(animal.getDateOfBirth());
        return animalRepository.save(existingAnimal);
    }

    @Override
    public void deleteAnimal(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        animalRepository.delete(animal);
    }

    @Override
    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @Override
    public List<Animal> findAnimalsByName(String name) {
        return animalRepository.findByNameContainingIgnoreCase(name);
    }
}
