package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aptech.dating.DTO.FamilyDTO;
import aptech.dating.DTO.UserPetDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.UserPet;
import aptech.dating.service.UserPetService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/userPet")
public class UserPetController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final UserPetService userPetService;

	// Use constructor-based dependency injection
	@Autowired
	public UserPetController(UserPetService userPetService) {
		this.userPetService = userPetService;
	}

	@GetMapping
	public ResponseEntity<List<UserPetDTO>> getAllUserPets() {
		List<UserPet> userPet = userPetService.getAllUserPets();

		List<UserPetDTO> userPetDTO = userPet.stream().map(element -> modelMapper.map(element, UserPetDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userPetDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserPetDTO> getUserPetById(@PathVariable int id) {
		Optional<UserPet> userPet = userPetService.getUserPetById(id);

		UserPetDTO userPetDTO = modelMapper.map(userPet, UserPetDTO.class);
		
		return userPetDTO!=null?ResponseEntity.ok(userPetDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<UserPet> createUserPet(@RequestBody @Validated UserPetDTO userPetDTO) {
		UserPet userPet = modelMapper.map(userPetDTO, UserPet.class);
		return ResponseEntity.ok(userPetService.saveUserPet(userPet));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserPet> updateUserPet(@PathVariable int id, @RequestBody @Validated UserPetDTO userPetDTO) {
		Optional<UserPet> userPet = userPetService.getUserPetById(id);

	    if (userPet.isPresent()) {
	    	UserPet updateUserPet = userPet.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(userPetDTO, updateUserPet);

	        // Save the updated admin
	        return ResponseEntity.ok(userPetService.saveUserPet(updateUserPet));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserPet(@PathVariable int id) {
		userPetService.deleteUserPet(id);
		return ResponseEntity.ok().build();
	}
}