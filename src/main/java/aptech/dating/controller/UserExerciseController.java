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
import aptech.dating.DTO.UserExerciseDTO;
import aptech.dating.DTO.UserMusicDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.UserExercise;
import aptech.dating.model.UserMusic;
import aptech.dating.service.UserExerciseService;

@RestController
@RequestMapping("/api/userExercise")
@CrossOrigin(origins = "http://localhost:4200")
public class UserExerciseController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final UserExerciseService userExerciseService;

	// Use constructor-based dependency injection
	@Autowired
	public UserExerciseController(UserExerciseService userExerciseService) {
		this.userExerciseService = userExerciseService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<UserExerciseDTO>> getAllUserExercises() {
		List<UserExercise> userExercise= userExerciseService.getAllUserExercises();

		List<UserExerciseDTO> userExerciseDTO = userExercise.stream().map(element -> modelMapper.map(element, UserExerciseDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userExerciseDTO);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<UserExerciseDTO> getUserExerciseById(@PathVariable int id) {
		Optional<UserExercise> userExercise = userExerciseService.getUserExerciseById(id);

		UserExerciseDTO userExerciseDTO = modelMapper.map(userExercise, UserExerciseDTO.class);
		
		return userExerciseDTO!=null?ResponseEntity.ok(userExerciseDTO):ResponseEntity.notFound().build();
	}
	
	@GetMapping("/exercise/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<UserExerciseDTO>> getUserExercisesByUserId(@PathVariable int id) {
		List<UserExercise> userExercise = userExerciseService.getUserExerciesByUserId(id);

		List<UserExerciseDTO> userExerciseDTO = userExercise.stream().map(element -> modelMapper.map(element, UserExerciseDTO.class))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(userExerciseDTO);
	}

	@PostMapping
	public ResponseEntity<UserExercise> createUserExercise(@RequestBody @Validated UserExerciseDTO userExerciseDTO) {
		UserExercise userExercise = modelMapper.map(userExerciseDTO, UserExercise.class);
		return ResponseEntity.ok(userExerciseService.saveUserExercise(userExercise));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<UserExercise> updateUserExercise(@PathVariable int id, @RequestBody @Validated UserExerciseDTO userExerciseDTO) {
		Optional<UserExercise> userExercise = userExerciseService.getUserExerciseById(id);

	    if (userExercise.isPresent()) {
	    	UserExercise updateUserExercise = userExercise.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(userExerciseDTO, updateUserExercise);

	        // Save the updated admin
	        return ResponseEntity.ok(userExerciseService.saveUserExercise(updateUserExercise));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteUserExercise(@PathVariable int id) {
		userExerciseService.deleteUserExercise(id);
		return ResponseEntity.ok().build();
	}
}