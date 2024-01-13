package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import aptech.dating.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
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
import aptech.dating.DTO.UserSignUpDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.User;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final UserService userService;
	private final StatusService statusService;
	private final DrinkingService drinkingService;
	private final SmokingService smokingService;
	private final FamilyService familyService;
	private final HabitService habitService;
	private final LiteracyService literacyService;
	private final PurposeService purposeService;
	private final WorkService workService;
	// Use constructor-based dependency injection
	@Autowired
	public UserController(UserService userService, StatusService statusService, DrinkingService drinkingService, SmokingService smokingService, FamilyService familyService, HabitService habitService, LiteracyService literacyService, PurposeService purposeService, WorkService workService) {
		this.userService = userService;
		this.statusService = statusService;
        this.drinkingService = drinkingService;
        this.smokingService = smokingService;
        this.familyService = familyService;
        this.habitService = habitService;
        this.literacyService = literacyService;
        this.purposeService = purposeService;
        this.workService = workService;
    }

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> user = userService.getAllUsers();

		List<UserDTO> userDTO = user.stream().map(element -> modelMapper.map(element, UserDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userDTO);
	}	
	
	@PostMapping("/signup")
	public void signUp(@RequestBody @Validated UserSignUpDTO userSignUpDTO) {
	    	//Optional<User> user = userService.getUserById(userSignUpDTO.getId());
		User user = modelMapper.map(userSignUpDTO, User.class);
		userService.signUpUser(userSignUpDTO.getEmail(),userSignUpDTO.getPassword());
		ResponseEntity.ok();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
		Optional<User> user = userService.getUserById(id);

		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		
		return userDTO!=null?ResponseEntity.ok(userDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<User> createUser(@RequestBody @Validated UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return ResponseEntity.ok(userService.saveUser(user));
	}
	@Transactional
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody @Validated UserDTO userDTO) {
		Optional<User> user = userService.getUserById(id);
		if (user.isPresent()) {
			//User updateUser = user.get();
//			updateUser.setWork(workService.getWorkByUserId(updateUser));
//			updateUser.setPurpose(purposeService.getPurposeByUserId(updateUser));
			//updateUser.setDrinking(updateUser.getDrinking());
//			updateUser.setSmoking(smokingService.getSmokingByUserId(updateUser));
//			updateUser.setFamily(familyService.getFamilyByUserId(updateUser));
//			updateUser.setLiteracy(literacyService.getLiteracyByUserId(updateUser));
//			updateUser.setHabit(habitService.getHabitByUserId(updateUser));
			modelMapper.map(userDTO, user);
			userService.banUser(statusService.getStatusByName("Banned").get().getId(), id);
			return ResponseEntity.ok(user.get());
		} else {
			// If the admin with the given ID is not found, return not found response
			return ResponseEntity.notFound().build();
		}
	}



	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
}