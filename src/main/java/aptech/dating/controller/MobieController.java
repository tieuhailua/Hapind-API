package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aptech.dating.DTO.ChoiceDTO;
import aptech.dating.DTO.UserDTO;
import aptech.dating.DTO.UserImageDTO;
import aptech.dating.model.Banned;
import aptech.dating.model.Drinking;
import aptech.dating.model.Family;
import aptech.dating.model.Habit;
import aptech.dating.model.Literacy;
import aptech.dating.model.Matching;
import aptech.dating.model.Purpose;
import aptech.dating.model.Reason;
import aptech.dating.model.Smoking;
import aptech.dating.model.User;
import aptech.dating.model.UserImage;
import aptech.dating.model.Work;
import aptech.dating.service.DrinkingService;
import aptech.dating.service.FamilyService;
import aptech.dating.service.HabitService;
import aptech.dating.service.LiteracyService;
import aptech.dating.service.MatchingService;
import aptech.dating.service.PurposeService;
import aptech.dating.service.SmokingService;
import aptech.dating.service.UserImageService;
import aptech.dating.service.UserService;
import aptech.dating.service.WorkService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/mobile")
@CrossOrigin(origins = "http://localhost:4200")
public class MobieController {
	@Autowired
	private ModelMapper modelMapper;

	private final UserService userService;
	private final DrinkingService drinkingService;
	private final SmokingService smokingService;
	private final FamilyService familyService;
	private final HabitService habitService;
	private final LiteracyService literacyService;
	private final PurposeService purposeService;
	private final WorkService workService;
	private final MatchingService matchingService;
	private final UserImageService userImageService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public MobieController(UserService userService, DrinkingService drinkingService, SmokingService smokingService,
			FamilyService familyService, HabitService habitService, LiteracyService literacyService,
			PurposeService purposeService, WorkService workService,MatchingService matchingService,UserImageService userImageService) {
		super();
		this.userService = userService;
		this.drinkingService = drinkingService;
		this.smokingService = smokingService;
		this.familyService = familyService;
		this.habitService = habitService;
		this.literacyService = literacyService;
		this.purposeService = purposeService;
		this.workService = workService;
		this.matchingService=matchingService;
		this.userImageService=userImageService;
	}

	@GetMapping("/getUser/{id}")
	public ResponseEntity<?> getUser(@PathVariable int id) {
		Optional<User> user = userService.getUserById(id);
		// Optional<User> userByPhone = userService.getUserByPhone(username);
		if (user.isPresent()) {
			if (user.get().getStatus().getName().equalsIgnoreCase("banned")) {
				Set<Banned> bans = user.get().getBanneds();
				for (Banned banned : bans) {
					Reason reason = banned.getReason();
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(reason.getName());
				}
				return ResponseEntity.noContent().build();
			} else {
				UserDTO userDTO = modelMapper.map(user, UserDTO.class);
				return userDTO != null ? ResponseEntity.ok(userDTO) : ResponseEntity.notFound().build();
			}
		}
//	if (userByPhone.isPresent()) {
//	    if (userByPhone.get().getStatus().getName().equalsIgnoreCase("banned")) {
//		return ResponseEntity.noContent().build();
//	    }
//	    UserDTO userDTO = modelMapper.map(userByPhone, UserDTO.class);
//	    return userDTO != null ? ResponseEntity.ok(userDTO) : ResponseEntity.notFound().build();
//	}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/getBanReason/{id}")
	public ResponseEntity<?> getBanReason(@PathVariable int id) {
		Optional<User> user = userService.getUserById(id);
		// Optional<User> userByPhone = userService.getUserByPhone(username);
		if (user.isPresent()) {
			if (user.get().getStatus().getName().equalsIgnoreCase("banned")) {
				Set<Banned> bans = user.get().getBanneds();
				for (Banned banned : bans) {
					Reason reason = banned.getReason();
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(reason.getName());
				}
				return ResponseEntity.noContent().build();
			}
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			return userDTO != null ? ResponseEntity.ok(userDTO) : ResponseEntity.notFound().build();
		}
//	if (userByPhone.isPresent()) {
//	    if (userByPhone.get().getStatus().getName().equalsIgnoreCase("banned")) {
//		return ResponseEntity.noContent().build();
//	    }
//	    UserDTO userDTO = modelMapper.map(userByPhone, UserDTO.class);
//	    return userDTO != null ? ResponseEntity.ok(userDTO) : ResponseEntity.notFound().build();
//	}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/getDrinkingChoice")
	public ResponseEntity<List<Drinking>> getDrinkingChoice() {
		return ResponseEntity.ok(drinkingService.getAllDrinkings());
	}

	@GetMapping("/getSmokingChoice")
	public ResponseEntity<List<Smoking>> getSmokingChoice() {
		return ResponseEntity.ok(smokingService.getAllSmokings());
	}

	@GetMapping("/getFamilyChoice")
	public ResponseEntity<List<Family>> getFamilyChoice() {
		return ResponseEntity.ok(familyService.getAllFamilys());
	}

	@GetMapping("/getHabitChoice")
	public ResponseEntity<List<Habit>> getHabitChoice() {
		return ResponseEntity.ok(habitService.getAllHabits());
	}

	@GetMapping("/getLiteracyChoice")
	public ResponseEntity<List<Literacy>> getLiteracyChoice() {
		return ResponseEntity.ok(literacyService.getAllLiteracys());
	}

	@GetMapping("/getPurposeChoice")
	public ResponseEntity<List<ChoiceDTO>> getPurposeChoice() {
		List<Purpose> user = purposeService.getAllPurposes();

		List<ChoiceDTO> userDTO = user.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(userDTO);
		// return ResponseEntity.ok(purposeService.getAllPurposes());
	}

	@GetMapping("/getWorkChoice")
	public ResponseEntity<List<Work>> getWorkChoice() {
		return ResponseEntity.ok(workService.getAllWorks());
	}

// sign-up
	@PostMapping("/signUp")
	public ResponseEntity<User> signUp(@RequestBody @Validated UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);

		// Bcrypt the password before saving
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		return ResponseEntity.ok(userService.saveUser(user));
	}
	
	@GetMapping("/getUserImageById/{id}")
	public ResponseEntity<List<String>> getUserImageById(@PathVariable int id) {
		List<String> userImage = userImageService.getImagePathsByUserId(id);
		
		return userImage!=null?ResponseEntity.ok(userImage):ResponseEntity.notFound().build();
	}	
	
	@GetMapping("/getRandomUser")
	public ResponseEntity<User> getRandomUser() {
		
		User user = modelMapper.map(new UserDTO(), User.class);

		return ResponseEntity.ok(userService.getRandomUser());
	}
	
	@GetMapping("/getMatchingTest")
	public ResponseEntity<List<Matching>> getMatchingTest() {
		
		//User user = modelMapper.map(new UserDTO(), User.class);

		return ResponseEntity.ok(matchingService.getAllMatchings());
	}
//Show MEss
//    @GetMapping("/getBanResson")
//    @PreAuthorize("hasAuthority('user')")
//    public ResponseEntity<UserDTO> getUserByPhone(String username) {
//	Optional<User> userByEmail = userService.getUserByEmail(username);
//	Optional<User> userByPhone = userService.getUserByPhone(username);
//
//    }

}
