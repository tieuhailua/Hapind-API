package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import aptech.dating.DTO.UserExpectingDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.UserExpecting;
import aptech.dating.service.UserExpectingService;

@RestController
@RequestMapping("/api/userExpecting")
@CrossOrigin(origins = "http://localhost:4200")
public class UserExpectingController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final UserExpectingService userExpectingService;

	// Use constructor-based dependency injection
	@Autowired
	public UserExpectingController(UserExpectingService userExpectingService) {
		this.userExpectingService = userExpectingService;
	}

	@GetMapping
	public ResponseEntity<List<UserExpectingDTO>> getAllUserExpectings() {
		List<UserExpecting> userExpecting = userExpectingService.getAllUserExpectings();

		List<UserExpectingDTO> userExpectingDTO = userExpecting.stream().map(element -> modelMapper.map(element, UserExpectingDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userExpectingDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserExpectingDTO> getUserExpectingById(@PathVariable int id) {
		Optional<UserExpecting> userExpecting = userExpectingService.getUserExpectingById(id);

		UserExpectingDTO userExpectingDTO = modelMapper.map(userExpecting, UserExpectingDTO.class);
		
		return userExpectingDTO!=null?ResponseEntity.ok(userExpectingDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<UserExpecting> createUserExpecting(@RequestBody @Validated UserExerciseDTO userExerciseDTO) {
		UserExpecting userExpecting = modelMapper.map(userExerciseDTO, UserExpecting.class);
		return ResponseEntity.ok(userExpectingService.saveUserExpecting(userExpecting));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserExpecting> updateUserExpecting(@PathVariable int id, @RequestBody @Validated UserExerciseDTO userExerciseDTO) {
		Optional<UserExpecting> userExpecting = userExpectingService.getUserExpectingById(id);

	    if (userExpecting.isPresent()) {
	    	UserExpecting updateUserExpecting = userExpecting.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(userExerciseDTO, updateUserExpecting);

	        // Save the updated admin
	        return ResponseEntity.ok(userExpectingService.saveUserExpecting(updateUserExpecting));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserExpecting(@PathVariable int id) {
		userExpectingService.deleteUserExpecting(id);
		return ResponseEntity.ok().build();
	}
}