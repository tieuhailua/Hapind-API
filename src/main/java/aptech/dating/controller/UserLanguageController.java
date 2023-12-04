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
import aptech.dating.DTO.UserLanguageDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.UserLanguage;
import aptech.dating.service.UserLanguageService;

@RestController
@RequestMapping("/api/userLanguage")
@CrossOrigin(origins = "http://localhost:4200")
public class UserLanguageController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final UserLanguageService userLanguageService;

	// Use constructor-based dependency injection
	@Autowired
	public UserLanguageController(UserLanguageService userLanguageService) {
		this.userLanguageService = userLanguageService;
	}

	@GetMapping
	public ResponseEntity<List<UserLanguageDTO>> getAllUserLanguages() {
		List<UserLanguage> userLanguage = userLanguageService.getAllUserLanguages();

		List<UserLanguageDTO> userLanguageDTO = userLanguage.stream().map(element -> modelMapper.map(element, UserLanguageDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userLanguageDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserLanguageDTO> getUserLanguageById(@PathVariable int id) {
		Optional<UserLanguage> userLanguage = userLanguageService.getUserLanguageById(id);

		UserLanguageDTO userLanguageDTO = modelMapper.map(userLanguage, UserLanguageDTO.class);
		
		return userLanguageDTO!=null?ResponseEntity.ok(userLanguageDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<UserLanguage> createUserLanguage(@RequestBody @Validated UserLanguageDTO userLanguageDTO) {
		UserLanguage userLanguage = modelMapper.map(userLanguageDTO, UserLanguage.class);
		return ResponseEntity.ok(userLanguageService.saveUserLanguage(userLanguage));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserLanguage> updateUserLanguage(@PathVariable int id, @RequestBody @Validated UserLanguageDTO userLanguageDTO) {
		Optional<UserLanguage> userLanguage = userLanguageService.getUserLanguageById(id);

	    if (userLanguage.isPresent()) {
	    	UserLanguage updateUserLanguage = userLanguage.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(userLanguageDTO, updateUserLanguage);

	        // Save the updated admin
	        return ResponseEntity.ok(userLanguageService.saveUserLanguage(updateUserLanguage));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserLanguage(@PathVariable int id) {
		userLanguageService.deleteUserLanguage(id);
		return ResponseEntity.ok().build();
	}
}