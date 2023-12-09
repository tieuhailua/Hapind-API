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
import aptech.dating.DTO.UserMusicDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.UserMusic;
import aptech.dating.service.UserMusicService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/userMusic")
@CrossOrigin(origins = "http://localhost:4200")
public class UserMusicController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final UserMusicService userMusicService;

	// Use constructor-based dependency injection
	@Autowired
	public UserMusicController(UserMusicService userMusicService) {
		this.userMusicService = userMusicService;
	}

	@GetMapping
	public ResponseEntity<List<UserMusicDTO>> getAllUserMusics() {
		List<UserMusic> userMusic = userMusicService.getAllUserMusics();

		List<UserMusicDTO> userMusicDTO = userMusic.stream().map(element -> modelMapper.map(element, UserMusicDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userMusicDTO);
	}
	@GetMapping("/{id}")
	public ResponseEntity<List<UserMusicDTO>> getUserMusicsByUserId(@PathVariable int id) {
		Optional<UserMusic> userMusic = userMusicService.getUserMusicById(id);

		List<UserMusicDTO> userMusicDTO = userMusic.stream().map(element -> modelMapper.map(element, UserMusicDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userMusicDTO);
	}
	@GetMapping("/music/{id}")
	public ResponseEntity<List<UserMusicDTO>> getUserMusicById(@PathVariable int id) {
		List<UserMusic> userMusic = userMusicService.getUserMusicsByUserId(id);

		List<UserMusicDTO> userMusicDTO = userMusic.stream().map(element -> modelMapper.map(element, UserMusicDTO.class))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(userMusicDTO);
	}

	@PostMapping
	public ResponseEntity<UserMusic> createUserMusic(@RequestBody @Validated UserMusicDTO userMusicDTO) {
		UserMusic userMusic = modelMapper.map(userMusicDTO, UserMusic.class);
		return ResponseEntity.ok(userMusicService.saveUserMusic(userMusic));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserMusic> updateUserMusic(@PathVariable int id, @RequestBody @Validated UserMusicDTO userMusicDTO) {
		Optional<UserMusic> userMusic = userMusicService.getUserMusicById(id);

	    if (userMusic.isPresent()) {
	    	UserMusic udpateUserMusic = userMusic.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(userMusicDTO, udpateUserMusic);

	        // Save the updated admin
	        return ResponseEntity.ok(userMusicService.saveUserMusic(udpateUserMusic));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserMusic(@PathVariable int id) {
		userMusicService.deleteUserMusic(id);
		return ResponseEntity.ok().build();
	}
}