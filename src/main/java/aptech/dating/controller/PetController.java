package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aptech.dating.DTO.FamilyDTO;
import aptech.dating.DTO.PetDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Pet;
import aptech.dating.service.PetService;

@RestController
@RequestMapping("/api/pet")
@CrossOrigin(origins = "http://localhost:4200")
public class PetController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final PetService petService;

	// Use constructor-based dependency injection
	@Autowired
	public PetController(PetService petService) {
		this.petService = petService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<PetDTO>> getAllPets() {
		List<Pet> pet = petService.getAllPets();

		List<PetDTO> petDTO = pet.stream().map(element -> modelMapper.map(element, PetDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(petDTO);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<PetDTO> getPetById(@PathVariable int id) {
		Optional<Pet> pet = petService.getPetById(id);

		PetDTO petDTO = modelMapper.map(pet, PetDTO.class);
		
		return petDTO!=null?ResponseEntity.ok(petDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Pet> createPet(@RequestBody @Validated PetDTO petDTO) {
		Pet pet = modelMapper.map(petDTO, Pet.class);
		return ResponseEntity.ok(petService.savePet(pet));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody @Validated PetDTO petDTO) {
		Optional<Pet> pet = petService.getPetById(id);

	    if (pet.isPresent()) {
	    	Pet updatePet = pet.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(petDTO, updatePet);

	        // Save the updated admin
	        return ResponseEntity.ok(petService.savePet(updatePet));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deletePet(@PathVariable int id) {
		petService.deletePet(id);
		return ResponseEntity.ok().build();
	}
}