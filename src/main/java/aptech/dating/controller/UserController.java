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
import aptech.dating.DTO.UserDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.User;
import aptech.dating.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final UserService userService;

	// Use constructor-based dependency injection
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> user = userService.getAllUsers();

		List<UserDTO> userDTO = user.stream().map(element -> modelMapper.map(element, UserDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
		Optional<User> user = userService.getUserById(id);

		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		
		return userDTO!=null?ResponseEntity.ok(userDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody @Validated UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return ResponseEntity.ok(userService.saveUser(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody @Validated UserDTO userDTO) {
		Optional<User> user = userService.getUserById(id);

	    if (user.isPresent()) {
	    	User updateUser = user.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(userDTO, updateUser);

	        // Save the updated admin
	        return ResponseEntity.ok(userService.saveUser(updateUser));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
}