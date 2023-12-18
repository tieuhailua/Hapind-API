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
import aptech.dating.DTO.UserConversationDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.UserConversation;
import aptech.dating.service.UserConversationService;

@RestController
@RequestMapping("/api/userConversation")
@CrossOrigin(origins = "http://localhost:4200")
public class UserConversationController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final UserConversationService userConversationService;

	// Use constructor-based dependency injection
	@Autowired
	public UserConversationController(UserConversationService userConversationService) {
		this.userConversationService = userConversationService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<UserConversationDTO>> getAllUserConversations() {
		List<UserConversation> userConversation = userConversationService.getAllUserConversations();

		List<UserConversationDTO> userConversationDTO = userConversation.stream().map(element -> modelMapper.map(element, UserConversationDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userConversationDTO);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<UserConversationDTO> getUserConversationById(@PathVariable int id) {
		Optional<UserConversation> userConversation = userConversationService.getUserConversationById(id);

		UserConversationDTO userConversationDTO = modelMapper.map(userConversation, UserConversationDTO.class);
		
		return userConversationDTO!=null?ResponseEntity.ok(userConversationDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<UserConversation> createUserConversation(@RequestBody @Validated UserConversationDTO userConversationDTO) {
		UserConversation userConversation = modelMapper.map(userConversationDTO, UserConversation.class);
		return ResponseEntity.ok(userConversationService.saveUserConversation(userConversation));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<UserConversation> updateUserConversation(@PathVariable int id, @RequestBody @Validated UserConversationDTO userConversationDTO) {
		Optional<UserConversation> userConversation = userConversationService.getUserConversationById(id);

	    if (userConversation.isPresent()) {
	    	UserConversation updateUserConversation = userConversation.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(userConversationDTO, updateUserConversation);

	        // Save the updated admin
	        return ResponseEntity.ok(userConversationService.saveUserConversation(updateUserConversation));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteUserConversation(@PathVariable int id) {
		userConversationService.deleteUserConversation(id);
		return ResponseEntity.ok().build();
	}
}