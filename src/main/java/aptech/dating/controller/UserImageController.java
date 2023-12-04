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
import aptech.dating.DTO.UserImageDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.UserImage;
import aptech.dating.service.UserImageService;

@RestController
@RequestMapping("/api/userImage")
public class UserImageController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final UserImageService userImageService;

	// Use constructor-based dependency injection
	@Autowired
	public UserImageController(UserImageService userImageService) {
		this.userImageService = userImageService;
	}

	@GetMapping
	public ResponseEntity<List<UserImageDTO>> getAllUserImages() {
		List<UserImage> userImage = userImageService.getAllUserImages();

		List<UserImageDTO> userImageDTO = userImage.stream().map(element -> modelMapper.map(element, UserImageDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userImageDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserImageDTO> getUserImageById(@PathVariable int id) {
		Optional<UserImage> userImage = userImageService.getUserImageById(id);

		UserImageDTO userImageDTO = modelMapper.map(userImage, UserImageDTO.class);
		
		return userImageDTO!=null?ResponseEntity.ok(userImageDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<UserImage> createUserImage(@RequestBody @Validated UserImageDTO userImageDTO) {
		UserImage userImage = modelMapper.map(userImageDTO, UserImage.class);
		return ResponseEntity.ok(userImageService.saveUserImage(userImage));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserImage> updateUserImage(@PathVariable int id, @RequestBody @Validated UserImageDTO userImageDTO) {
		Optional<UserImage> userImage = userImageService.getUserImageById(id);

	    if (userImage.isPresent()) {
	    	UserImage updateUserImage = userImage.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(userImageDTO, updateUserImage);

	        // Save the updated admin
	        return ResponseEntity.ok(userImageService.saveUserImage(updateUserImage));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserImage(@PathVariable int id) {
		userImageService.deleteUserImage(id);
		return ResponseEntity.ok().build();
	}
}