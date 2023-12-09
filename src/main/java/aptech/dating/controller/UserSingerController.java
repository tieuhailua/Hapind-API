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
import aptech.dating.DTO.UserSingerDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.UserMusic;
import aptech.dating.model.UserSinger;
import aptech.dating.service.UserSingerService;

@RestController
@RequestMapping("/api/userSinger")
@CrossOrigin(origins = "http://localhost:4200")
public class UserSingerController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final UserSingerService userSingerService;

	// Use constructor-based dependency injection
	@Autowired
	public UserSingerController(UserSingerService userSingerService) {
		this.userSingerService = userSingerService;
	}

	@GetMapping
	public ResponseEntity<List<UserSingerDTO>> getAllUserSingers() {
		List<UserSinger> userSinger = userSingerService.getAllUserSingers();

		List<UserSingerDTO> userSingerDTO = userSinger.stream().map(element -> modelMapper.map(element, UserSingerDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userSingerDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserSingerDTO> getUserSingerById(@PathVariable int id) {
		Optional<UserSinger> userSinger = userSingerService.getUserSingerById(id);

		UserSingerDTO userSingerDTO = modelMapper.map(userSinger, UserSingerDTO.class);
		
		return userSingerDTO!=null?ResponseEntity.ok(userSingerDTO):ResponseEntity.notFound().build();
	}
	
	@GetMapping("/singer/{id}")
	public ResponseEntity<List<UserSingerDTO>> getUserSingersByUserId(@PathVariable int id) {
		List<UserSinger> userSinger = userSingerService.getUserSingersByUserId(id);

		List<UserSingerDTO> userSingerDTO = userSinger.stream().map(element -> modelMapper.map(element, UserSingerDTO.class))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(userSingerDTO);
	}
	
	@PostMapping
	public ResponseEntity<UserSinger> createUserSinger(@RequestBody @Validated UserSingerDTO userSingerDTO) {
		UserSinger userSinger = modelMapper.map(userSingerDTO, UserSinger.class);
		return ResponseEntity.ok(userSingerService.saveUserSinger(userSinger));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserSinger> updateUserSinger(@PathVariable int id, @RequestBody @Validated UserSingerDTO userSingerDTO) {
		Optional<UserSinger> userSinger = userSingerService.getUserSingerById(id);

	    if (userSinger.isPresent()) {
	    	UserSinger updateUserSinger = userSinger.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(userSingerDTO, updateUserSinger);

	        // Save the updated admin
	        return ResponseEntity.ok(userSingerService.saveUserSinger(updateUserSinger));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserSinger(@PathVariable int id) {
		userSingerService.deleteUserSinger(id);
		return ResponseEntity.ok().build();
	}
}