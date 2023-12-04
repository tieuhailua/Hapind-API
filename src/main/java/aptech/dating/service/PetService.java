package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.PetDTO;
import aptech.dating.model.Pet;
import aptech.dating.repository.PetRepository;

@Service
public class PetService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final PetRepository petRepository;

    // Use constructor-based dependency injection
    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(int id) {
        return petRepository.findById(id);
    }

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public void deletePet(int id) {
        petRepository.deleteById(id);
    }
    
    public PetDTO getPet(int id) { 
        Pet pet = this.petRepository.findById(id).get(); 
        PetDTO petDto = this.modelMapper.map(pet, PetDTO.class); 
        return petDto; 
    } 
}