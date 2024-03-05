package aptech.dating.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aptech.dating.DTO.AdminDTO;
import aptech.dating.DTO.BlockDTO;
import aptech.dating.DTO.BlogDTO;
import aptech.dating.DTO.ChoiceDTO;
import aptech.dating.DTO.MusicDTO;
import aptech.dating.DTO.ReasonDTO;
import aptech.dating.DTO.ReportDTO;
import aptech.dating.DTO.ReportRequest;
import aptech.dating.DTO.StatusDTO;
import aptech.dating.DTO.UserDTO;
import aptech.dating.DTO.UserExerciseDTO;
import aptech.dating.DTO.UserExpectingDTO;
import aptech.dating.DTO.UserHobbyDTO;
import aptech.dating.DTO.UserImageDTO;
import aptech.dating.DTO.UserLanguageDTO;
import aptech.dating.DTO.UserMusicDTO;
import aptech.dating.DTO.UserPetDTO;
import aptech.dating.DTO.UserRegisterDTO;
import aptech.dating.DTO.UserSingerDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Block;
import aptech.dating.model.Blog;
import aptech.dating.model.Drinking;
import aptech.dating.model.Evidence;
import aptech.dating.model.Exercise;
import aptech.dating.model.Expecting;
import aptech.dating.model.Family;
import aptech.dating.model.Habit;
import aptech.dating.model.Hobby;
import aptech.dating.model.Language;
import aptech.dating.model.Literacy;
import aptech.dating.model.Matching;
import aptech.dating.model.Music;
import aptech.dating.model.Pet;
import aptech.dating.model.Purpose;
import aptech.dating.model.Reason;
import aptech.dating.model.Report;
import aptech.dating.model.Singer;
import aptech.dating.model.Smoking;
import aptech.dating.model.Status;
import aptech.dating.model.User;
import aptech.dating.model.UserExercise;
import aptech.dating.model.UserExpecting;
import aptech.dating.model.UserHobby;
import aptech.dating.model.UserImage;
import aptech.dating.model.UserLanguage;
import aptech.dating.model.UserMusic;
import aptech.dating.model.UserPet;
import aptech.dating.model.UserSinger;
import aptech.dating.model.Work;
import aptech.dating.service.BlockService;
import aptech.dating.service.BlogService;
import aptech.dating.service.DrinkingService;
import aptech.dating.service.EvidenceService;
import aptech.dating.service.ExerciseService;
import aptech.dating.service.ExpectingService;
import aptech.dating.service.FamilyService;
import aptech.dating.service.HabitService;
import aptech.dating.service.HobbyService;
import aptech.dating.service.LanguageService;
import aptech.dating.service.LiteracyService;
import aptech.dating.service.MatchingService;
import aptech.dating.service.MusicService;
import aptech.dating.service.PetService;
import aptech.dating.service.PurposeService;
import aptech.dating.service.ReasonService;
import aptech.dating.service.ReportService;
import aptech.dating.service.SingerService;
import aptech.dating.service.SmokingService;
import aptech.dating.service.StatusService;
import aptech.dating.service.UserExerciseService;
import aptech.dating.service.UserExpectingService;
import aptech.dating.service.UserHobbyService;
import aptech.dating.service.UserImageService;
import aptech.dating.service.UserLanguageService;
import aptech.dating.service.UserMusicService;
import aptech.dating.service.UserPetService;
import aptech.dating.service.UserService;
import aptech.dating.service.UserSingerService;
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
	private final StatusService statusService;
	private final MatchingService matchingService;
	private final UserImageService userImageService;
	private final BlogService blogService;
	private final ReportService reportService;
	private final BlockService blockService;
	private final MusicService musicService;
	private final SingerService singerService;
	private final PetService petService;
	private final LanguageService languageService;
	private final HobbyService hobbyService;
	private final ExerciseService exerciseService;
	private final EvidenceService evidenceService;
	private final ExpectingService expectingService;
	private final ReasonService reasonService;
	private final UserMusicService userMusicService;
	private final UserSingerService userSingerService;
	private final UserPetService userPetService;
	private final UserLanguageService userLanguageService;
	private final UserHobbyService userHobbyService;
	private final UserExerciseService userExerciseService;
	private final UserExpectingService userExpectingService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public MobieController(UserService userService, DrinkingService drinkingService, SmokingService smokingService,
			FamilyService familyService, HabitService habitService, LiteracyService literacyService,
			PurposeService purposeService, WorkService workService, StatusService statusService,
			MatchingService matchingService, UserImageService userImageService, MusicService musicService,
			SingerService singerService, PetService petService, LanguageService languageService,
			HobbyService hobbyService, ExerciseService exerciseService, ExpectingService expectingService,
			UserMusicService userMusicService, UserSingerService userSingerService, UserPetService userPetService,
			UserLanguageService userLanguageService, UserHobbyService userHobbyService,
			UserExerciseService userExerciseService, UserExpectingService userExpectingService, BlogService blogService,
			ReportService reportService, BlockService blockService, ReasonService reasonService,
			EvidenceService evidenceService) {
		super();
		this.userService = userService;
		this.drinkingService = drinkingService;
		this.smokingService = smokingService;
		this.familyService = familyService;
		this.habitService = habitService;
		this.literacyService = literacyService;
		this.purposeService = purposeService;
		this.workService = workService;
		this.statusService = statusService;
		this.matchingService = matchingService;
		this.userImageService = userImageService;
		this.musicService = musicService;
		this.singerService = singerService;
		this.petService = petService;
		this.blogService = blogService;
		this.evidenceService = evidenceService;
		this.reasonService = reasonService;
		this.reportService = reportService;
		this.blockService = blockService;
		this.languageService = languageService;
		this.hobbyService = hobbyService;
		this.exerciseService = exerciseService;
		this.expectingService = expectingService;
		this.userMusicService = userMusicService;
		this.userSingerService = userSingerService;
		this.userPetService = userPetService;
		this.userLanguageService = userLanguageService;
		this.userHobbyService = userHobbyService;
		this.userExerciseService = userExerciseService;
		this.userExpectingService = userExpectingService;
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
			} else if (user.get().getStatus().getName().equalsIgnoreCase("deleted")) {
				return ResponseEntity.badRequest().build();
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

	@GetMapping("/getChoice/{filter}")
	public ResponseEntity<List<ChoiceDTO>> getChoice(@PathVariable String filter) {
		List<ChoiceDTO> userDTO;
		switch (filter) {
		case "Purpose":
			List<Purpose> purposes = purposeService.getAllPurposes();
			userDTO = purposes.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		case "Drinking":
			List<Drinking> drinkings = drinkingService.getAllDrinkings();
			userDTO = drinkings.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		case "Smoking":
			List<Smoking> smoking = smokingService.getAllSmokings();
			userDTO = smoking.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		case "Habit":
			List<Habit> habit = habitService.getAllHabits();
			userDTO = habit.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		case "Literacy":
			List<Literacy> literacy = literacyService.getAllLiteracys();
			userDTO = literacy.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		case "Work":
			List<Work> work = workService.getAllWorks();
			userDTO = work.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		case "Family":
			List<Family> family = familyService.getAllFamilys();
			userDTO = family.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		// one-many
		case "Music":
			List<Music> music = musicService.getAllMusics();
			userDTO = music.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		case "Singer":
			List<Singer> singer = singerService.getAllSingers();
			userDTO = singer.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		case "Pet":
			List<Pet> pet = petService.getAllPets();
			userDTO = pet.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		case "Language":
			List<Language> languages = languageService.getAllLanguages();
			userDTO = languages.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		case "Hobby":
			List<Hobby> hobby = hobbyService.getAllHobbys();
			userDTO = hobby.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		case "Exercise":
			List<Exercise> exercises = exerciseService.getAllExercises();
			userDTO = exercises.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		case "Expecting":
			List<Expecting> expectings = expectingService.getAllExpectings();
			userDTO = expectings.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
					.collect(Collectors.toList());
			break;
		default:
			userDTO = new ArrayList<>();
			break;
		}
		return ResponseEntity.ok(userDTO);

//		
//		List<Purpose> user = purposeService.getAllPurposes();
//
//		List<ChoiceDTO> userDTO = user.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
//				.collect(Collectors.toList());
//
//		return ResponseEntity.ok(userDTO);
	}

	@GetMapping("/getMultiChoice/{userId}/{filter}")
	public ResponseEntity<List<?>> getMultiChoice(@PathVariable int userId, @PathVariable String filter) {
		switch (filter) {
		// one-many
		case "Music":
			List<UserMusic> userMusic = userMusicService.getUserMusicsByUserId(userId);
			List<UserMusicDTO> userMusicDTO = userMusic.stream()
					.map(element -> modelMapper.map(element, UserMusicDTO.class)).collect(Collectors.toList());
			return ResponseEntity.ok(userMusicDTO);
		case "Pet":
			List<UserPet> userPet = userPetService.getChooseUserPetsByUserId(userId);
			List<UserPetDTO> userPetDTO = userPet.stream().map(element -> modelMapper.map(element, UserPetDTO.class))
					.collect(Collectors.toList());
			return ResponseEntity.ok(userPetDTO);
		case "Singer":
			List<UserSinger> userSinger = userSingerService.getUserSingersByUserId(userId);
			List<UserSingerDTO> userSingerDTO = userSinger.stream()
					.map(element -> modelMapper.map(element, UserSingerDTO.class)).collect(Collectors.toList());
			return ResponseEntity.ok(userSingerDTO);
		case "Language":
			List<UserLanguage> userLanguage = userLanguageService.getChooseUserLanguagesByUserId(userId);
			List<UserLanguageDTO> userLanguageDTO = userLanguage.stream()
					.map(element -> modelMapper.map(element, UserLanguageDTO.class)).collect(Collectors.toList());
			return ResponseEntity.ok(userLanguageDTO);
		case "Exercise":
			List<UserExercise> userExercise = userExerciseService.getChooseUserExercisesByUserId(userId);
			List<UserExerciseDTO> userExerciseDTO = userExercise.stream()
					.map(element -> modelMapper.map(element, UserExerciseDTO.class)).collect(Collectors.toList());
			return ResponseEntity.ok(userExerciseDTO);
		case "Expecting":
			List<UserExpecting> userExpecting = userExpectingService.getChooseUserExercisesByUserId(userId);
			List<UserExpectingDTO> userExpectingDTO = userExpecting.stream()
					.map(element -> modelMapper.map(element, UserExpectingDTO.class)).collect(Collectors.toList());
			return ResponseEntity.ok(userExpectingDTO);
		case "Hobby":
			List<UserHobby> userHobby = userHobbyService.getChooseUserHobbysByUserId(userId);
			List<UserHobbyDTO> userHobbyDTO = userHobby.stream()
					.map(element -> modelMapper.map(element, UserHobbyDTO.class)).collect(Collectors.toList());
			return ResponseEntity.ok(userHobbyDTO);
//		case "UserSinger":
//			List<UserSinger> userSinger = userMusicService.getAllUserMusics();
//			List<UserSingerDTO> userSingerDTO = userMusic.stream()
//					.map(element -> modelMapper.map(element, UserSingerDTO.class)).collect(Collectors.toList());
//			return ResponseEntity.ok(userSingerDTO);
//		case "UserPet":
//			List<UserMusic> userMusic = userMusicService.getAllUserMusics();
//			List<UserMusicDTO> userMusicDTO = userMusic.stream()
//					.map(element -> modelMapper.map(element, UserMusicDTO.class)).collect(Collectors.toList());
//			return ResponseEntity.ok(userMusicDTO);
//		case "UserLanguage":
//			List<UserMusic> userMusic = userMusicService.getAllUserMusics();
//			List<UserMusicDTO> userMusicDTO = userMusic.stream()
//					.map(element -> modelMapper.map(element, UserMusicDTO.class)).collect(Collectors.toList());
//			return ResponseEntity.ok(userMusicDTO);
//		case "UserHobby":
//			List<UserMusic> userMusic = userMusicService.getAllUserMusics();
//			List<UserMusicDTO> userMusicDTO = userMusic.stream()
//					.map(element -> modelMapper.map(element, UserMusicDTO.class)).collect(Collectors.toList());
//			return ResponseEntity.ok(userMusicDTO);
//		case "UserExercise":
//			List<UserMusic> userMusic = userMusicService.getAllUserMusics();
//			List<UserMusicDTO> userMusicDTO = userMusic.stream()
//					.map(element -> modelMapper.map(element, UserMusicDTO.class)).collect(Collectors.toList());
//			return ResponseEntity.ok(userMusicDTO);
//		case "UserExpecting":
//			List<UserMusic> userMusic = userMusicService.getAllUserMusics();
//			List<UserMusicDTO> userMusicDTO = userMusic.stream()
//					.map(element -> modelMapper.map(element, UserMusicDTO.class)).collect(Collectors.toList());
//			return ResponseEntity.ok(userMusicDTO);
		default:
			return null;
		}
//		
//		List<Purpose> user = purposeService.getAllPurposes();
//
//		List<ChoiceDTO> userDTO = user.stream().map(element -> modelMapper.map(element, ChoiceDTO.class))
//				.collect(Collectors.toList());
//
//		return ResponseEntity.ok(userDTO);
	}

	@Transactional
	@PutMapping("/updateUserStatus/{id}/{status}")
	public ResponseEntity<?> updateUserStatus(@PathVariable Integer id, @PathVariable String status) {
		Optional<User> user = userService.getUserById(id);
		if (user.isPresent()) {
			// User updateUser = user.get();
//			updateUser.setWork(workService.getWorkByUserId(updateUser));
//			updateUser.setPurpose(purposeService.getPurposeByUserId(updateUser));
			// updateUser.setDrinking(updateUser.getDrinking());
//			updateUser.setSmoking(smokingService.getSmokingByUserId(updateUser));
//			updateUser.setFamily(familyService.getFamilyByUserId(updateUser));
//			updateUser.setLiteracy(literacyService.getLiteracyByUserId(updateUser));
//			updateUser.setHabit(habitService.getHabitByUserId(updateUser));
			userService.banUser(statusService.getStatusByName(status).get().getId(), id);
			// bannedService.saveBanned(new Banned());
			return ResponseEntity.ok().build();
		} else {
			// If the admin with the given ID is not found, return not found response
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/updateUser/{id}/{name}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @PathVariable String name) {
		Optional<User> user = userService.getUserById(id);
		if (user.isPresent()) {

			Purpose purpose = purposeService.getPurposeByName(name);
			Drinking drinking = drinkingService.getDrinkingByName(name);
			Smoking smoking = smokingService.getDrinkingByName(name);
			Habit habit = habitService.getHabitByName(name);
			Literacy literacy = literacyService.getLiteracyByName(name);
			Work work = workService.getWorkByName(name);
			Family family = familyService.getFamilyByName(name);

			User updateUser = user.get();

			if (name.startsWith("des") || name.startsWith("adr")) {
				if (name.startsWith("des")) {
					String updatedName1 = name.substring(name.toLowerCase().indexOf("des") + 3);
					updateUser.setDescription(updatedName1);
				}
				if (name.startsWith("adr")) {
					String updatedName2 = name.substring(name.toLowerCase().indexOf("adr") + 3);
					updateUser.setAddress(updatedName2);
				}

			}

//			if ( name.startsWith("adr")) {
//				String updatedName = name.substring(name.toLowerCase().indexOf("adr") + 3);
//				updateUser.setAddress(updatedName);
//			}

			if (name.startsWith("Height is:")) {
				// Extract the value after "Height is: "
				String heightValue = name.substring("Height is: ".length());
				updateUser.setHeight(Integer.parseInt(heightValue));
			}

			if (name.startsWith("Weight is:")) {
				// Extract the value after "Weight is: "
				String weightValue = name.substring("Weight is: ".length());
				updateUser.setWeight(Integer.parseInt(weightValue));
			}
			if (name.equals("Male") || name.equals("Female") || name.equals("Everyone")) {
				updateUser.setFinding(name);
			}
			if (name.equals("Aries") || name.equals("Taurus") || name.equals("Gemini") || name.equals("Cancer")
					|| name.equals("Leo") || name.equals("Virgo") || name.equals("Libra") || name.equals("Scorpio")
					|| name.equals("Sagittarius") || name.equals("Capricorn") || name.equals("Aquarius")
					|| name.equals("Pisces")) {
				updateUser.setZodiac(name);
			}
			if (name.equals("Male") | name.equals("Female")) {
				updateUser.setGender(name);
			}
			if (purpose != null) {
				updateUser.setPurpose(purpose);
			}
			if (drinking != null) {
				updateUser.setDrinking(drinking);
			}

			if (smoking != null) {
				updateUser.setSmoking(smoking);
			}

			if (habit != null) {
				updateUser.setHabit(habit);
			}

			if (literacy != null) {
				updateUser.setLiteracy(literacy);
			}

			if (work != null) {
				updateUser.setWork(work);
			}

			if (family != null) {
				updateUser.setFamily(family);
			}

//			userDTO.setCreateTime(updateUser.getCreateTime());
//			userDTO.setDob(updateUser.getDob());
//			userDTO.setLastLogin(updateUser.getLastLogin());
			// Update the existingAdmin with the data from adminDTO
			return ResponseEntity.ok(userService.saveUser(updateUser));
		} else {
			// If the admin with the given ID is not found, return not found response
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/updateUser/music/{id}")
	@SuppressWarnings("unchecked")
	public ResponseEntity<User> updateMusicUser(@PathVariable int id, @RequestBody Map<String, Object> requestBody) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<String> names = ((List<Object>) requestBody.get("names")).stream().map(String::valueOf)
					.collect(Collectors.toList());
			List<UserMusic> existingUserMusics = userMusicService.getUserMusicsByUserId(id);
			for (UserMusic existingUserMusic : existingUserMusics) {
				String musicName = existingUserMusic.getMusic().getName();
				boolean isNameInList = names.contains(musicName);
				existingUserMusic.setChoose(isNameInList);
			}
			for (String name : names) {
				UserMusic existingChoiceUserMusic = userMusicService.getUserMusicByUserAndMusicName(user, name);
				if (existingChoiceUserMusic != null) {
					// If UserMusic already exists, update the choose property
					existingChoiceUserMusic.setChoose(true);
					userMusicService.saveUserMusic(existingChoiceUserMusic);
				} else {
					// If UserMusic does not exist, create a new one
					Music music = musicService.getMusicByName(name);
					if (music != null) {
						UserMusic newUserMusic = new UserMusic();
						newUserMusic.setUser(user);
						newUserMusic.setMusic(music);
						newUserMusic.setChoose(true);
						userMusicService.saveUserMusic(newUserMusic);
					}
				}
			}
			return ResponseEntity.ok(userService.saveUser(user));
		} else {
			// If the user with the given ID is not found, return not found response
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/updateUser/singer/{id}")
	@SuppressWarnings("unchecked")
	public ResponseEntity<User> updateSingerUser(@PathVariable int id, @RequestBody Map<String, Object> requestBody) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<String> names = ((List<Object>) requestBody.get("names")).stream().map(String::valueOf)
					.collect(Collectors.toList());
			List<UserSinger> existingUserMusics = userSingerService.getUserSingersByUserId(id);
			for (UserSinger existingUserMusic : existingUserMusics) {
				String musicName = existingUserMusic.getSinger().getName();
				boolean isNameInList = names.contains(musicName);
				existingUserMusic.setChoose(isNameInList);
			}
			for (String name : names) {
				UserSinger existingChoiceUserMusic = userSingerService.getUserSingerByUserAndSingerName(user, name);
				if (existingChoiceUserMusic != null) {
					// If UserMusic already exists, update the choose property
					existingChoiceUserMusic.setChoose(true);
					userSingerService.saveUserSinger(existingChoiceUserMusic);
				} else {
					// If UserMusic does not exist, create a new one
					Singer music = singerService.getSingerByName(name);
					if (music != null) {
						UserSinger newUserMusic = new UserSinger();
						newUserMusic.setUser(user);
						newUserMusic.setSinger(music);
						newUserMusic.setChoose(true);
						userSingerService.saveUserSinger(newUserMusic);
					}
				}
			}
			return ResponseEntity.ok(userService.saveUser(user));
		} else {
			// If the user with the given ID is not found, return not found response
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/updateUser/pet/{id}")
	@SuppressWarnings("unchecked")
	public ResponseEntity<User> updatePetUser(@PathVariable int id, @RequestBody Map<String, Object> requestBody) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<String> names = ((List<Object>) requestBody.get("names")).stream().map(String::valueOf)
					.collect(Collectors.toList());
			List<UserPet> existingUserMusics = userPetService.getChooseUserPetsByUserId(id);
			for (UserPet existingUserMusic : existingUserMusics) {
				String musicName = existingUserMusic.getPet().getName();
				boolean isNameInList = names.contains(musicName);
				existingUserMusic.setChoose(isNameInList);
			}
			for (String name : names) {
				UserPet existingChoiceUserMusic = userPetService.getUserPetByUserAndPetName(user, name);
				if (existingChoiceUserMusic != null) {
					// If UserMusic already exists, update the choose property
					existingChoiceUserMusic.setChoose(true);
					userPetService.saveUserPet(existingChoiceUserMusic);
				} else {
					// If UserMusic does not exist, create a new one
					Pet music = petService.getPetByName(name);
					if (music != null) {
						UserPet newUserMusic = new UserPet();
						newUserMusic.setUser(user);
						newUserMusic.setPet(music);
						newUserMusic.setChoose(true);
						userPetService.saveUserPet(newUserMusic);
					}
				}
			}
			return ResponseEntity.ok(userService.saveUser(user));
		} else {
			// If the user with the given ID is not found, return not found response
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/updateUser/language/{id}")
	@SuppressWarnings("unchecked")
	public ResponseEntity<User> updateLanguageUser(@PathVariable int id, @RequestBody Map<String, Object> requestBody) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<String> names = ((List<Object>) requestBody.get("names")).stream().map(String::valueOf)
					.collect(Collectors.toList());
			List<UserLanguage> existingUserMusics = userLanguageService.getChooseUserLanguagesByUserId(id);
			for (UserLanguage existingUserMusic : existingUserMusics) {
				String musicName = existingUserMusic.getLanguage().getName();
				boolean isNameInList = names.contains(musicName);
				existingUserMusic.setChoose(isNameInList);
			}
			for (String name : names) {
				UserLanguage existingChoiceUserMusic = userLanguageService.getUserLanguageByUserAndLanguageName(user,
						name);
				if (existingChoiceUserMusic != null) {
					// If UserMusic already exists, update the choose property
					existingChoiceUserMusic.setChoose(true);
					userLanguageService.saveUserLanguage(existingChoiceUserMusic);
				} else {
					Language music = languageService.getLanguageByName(name);
					if (music != null) {
						UserLanguage newUserMusic = new UserLanguage();
						newUserMusic.setUser(user);
						newUserMusic.setLanguage(music);
						newUserMusic.setChoose(true);
						userLanguageService.saveUserLanguage(newUserMusic);
					}
				}
			}
			return ResponseEntity.ok(userService.saveUser(user));
		} else {
			// If the user with the given ID is not found, return not found response
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/updateUser/hobby/{id}")
	@SuppressWarnings("unchecked")
	public ResponseEntity<User> updateHobbyUser(@PathVariable int id, @RequestBody Map<String, Object> requestBody) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<String> names = ((List<Object>) requestBody.get("names")).stream().map(String::valueOf)
					.collect(Collectors.toList());
			List<UserHobby> existingUserMusics = userHobbyService.getChooseUserHobbysByUserId(id);
			for (UserHobby existingUserMusic : existingUserMusics) {
				String musicName = existingUserMusic.getHobby().getName();
				boolean isNameInList = names.contains(musicName);
				existingUserMusic.setChoose(isNameInList);
			}
			for (String name : names) {
				UserHobby existingChoiceUserMusic = userHobbyService.getUserHobbyByUserAndHobbyName(user, name);
				if (existingChoiceUserMusic != null) {
					existingChoiceUserMusic.setChoose(true);
					userHobbyService.saveUserHobby(existingChoiceUserMusic);
				} else {
					Hobby music = hobbyService.getHobbyByName(name);
					if (music != null) {
						UserHobby newUserMusic = new UserHobby();
						newUserMusic.setUser(user);
						newUserMusic.setHobby(music);
						newUserMusic.setChoose(true);
						userHobbyService.saveUserHobby(newUserMusic);
					}
				}
			}
			return ResponseEntity.ok(userService.saveUser(user));
		} else {
			// If the user with the given ID is not found, return not found response
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/updateUser/exercise/{id}")
	@SuppressWarnings("unchecked")
	public ResponseEntity<User> updateExcersiseUser(@PathVariable int id,
			@RequestBody Map<String, Object> requestBody) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<String> names = ((List<Object>) requestBody.get("names")).stream().map(String::valueOf)
					.collect(Collectors.toList());
			List<UserExercise> existingUserMusics = userExerciseService.getChooseUserExercisesByUserId(id);
			for (UserExercise existingUserMusic : existingUserMusics) {
				String musicName = existingUserMusic.getExercise().getName();
				boolean isNameInList = names.contains(musicName);
				existingUserMusic.setChoose(isNameInList);
			}
			for (String name : names) {
				UserExercise existingChoiceUserMusic = userExerciseService.getUserExerciseByUserAndExerciseName(user,
						name);
				if (existingChoiceUserMusic != null) {
					existingChoiceUserMusic.setChoose(true);
					userExerciseService.saveUserExercise(existingChoiceUserMusic);
				} else {
					Exercise music = exerciseService.getExerciseByName(name);
					if (music != null) {
						UserExercise newUserMusic = new UserExercise();
						newUserMusic.setUser(user);
						newUserMusic.setExercise(music);
						newUserMusic.setChoose(true);
						userExerciseService.saveUserExercise(newUserMusic);
					}
				}
			}
			return ResponseEntity.ok(userService.saveUser(user));
		} else {
			// If the user with the given ID is not found, return not found response
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/updateUser/expecting/{id}")
	@SuppressWarnings("unchecked")
	public ResponseEntity<User> updateExpectingUser(@PathVariable int id,
			@RequestBody Map<String, Object> requestBody) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<String> names = ((List<Object>) requestBody.get("names")).stream().map(String::valueOf)
					.collect(Collectors.toList());
			List<UserExpecting> existingUserMusics = userExpectingService.getChooseUserExercisesByUserId(id);
			for (UserExpecting existingUserMusic : existingUserMusics) {
				String musicName = existingUserMusic.getExpecting().getName();
				boolean isNameInList = names.contains(musicName);
				existingUserMusic.setChoose(isNameInList);
			}
			for (String name : names) {
				UserExpecting existingChoiceUserMusic = userExpectingService.getUserExerciseByUserAndExerciseName(user,
						name);
				if (existingChoiceUserMusic != null) {
					// If UserMusic already exists, update the choose property
					existingChoiceUserMusic.setChoose(true);
					userExpectingService.saveUserExpecting(existingChoiceUserMusic);
				} else {
					// If UserMusic does not exist, create a new one
					Expecting music = expectingService.getExpectingByName(name);
					if (music != null) {
						UserExpecting newUserMusic = new UserExpecting();
						newUserMusic.setUser(user);
						newUserMusic.setExpecting(music);
						newUserMusic.setChoose(true);
						userExpectingService.saveUserExpecting(newUserMusic);
					}
				}
			}
			return ResponseEntity.ok(userService.saveUser(user));
		} else {
			// If the user with the given ID is not found, return not found response
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/getWorkChoice")
	public ResponseEntity<List<Work>> getWorkChoice() {
		return ResponseEntity.ok(workService.getAllWorks());
	}

// sign-up
	@PostMapping("/signUp")
	public ResponseEntity<User> signUp(@RequestBody @Validated UserRegisterDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		// Bcrypt the password before saving
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setPurpose(purposeService.getPurposeById(userDTO.getPurpose()).get());
		user.setStatus(statusService.getStatusById(userDTO.getStatus()).get());
		return ResponseEntity.ok(userService.saveUser(user));
	}

	@GetMapping("/getUserImageById/{id}")
	public ResponseEntity<List<UserImage>> getUserImageById(@PathVariable int id) {
		List<UserImage> userImage = userImageService.getImagePathsByUserId(id);

		return userImage != null ? ResponseEntity.ok(userImage) : ResponseEntity.notFound().build();
	}

	@PostMapping("/addOrUpdateUserImage/{userId}")
	public ResponseEntity<UserImage> addOrUpdateUserImage(@RequestBody @Validated UserImageDTO userImageDTO,
			@PathVariable int userId) {
		UserImage userImage = modelMapper.map(userImageDTO, UserImage.class);
		userImage.setUser(userService.getUserById(userId).get());
		// User user =userImage.getUser();
		// user.setCreateTime(java.sql.Date.valueOf(user.getCreateTime()));
		return ResponseEntity.ok(userImageService.addOrUpdateUserImage(userImage));
	}

	@PostMapping("/matching/{id}/{email}/{status}")
	public ResponseEntity<Matching> matching(@PathVariable int id, @PathVariable String email,
			@PathVariable int status) {
		Matching matching = new Matching(userService.getUserById(id).get(), userService.getUserByEmail(email).get(),
				status);
		return ResponseEntity.ok(matchingService.saveMatching(matching));
	}

	@PutMapping("/dating/{firstUserId}/{secondUserId}/{status}")
	public ResponseEntity<?> dating(@PathVariable int firstUserId, @PathVariable int secondUserId,
			@PathVariable int status) {
		return ResponseEntity.ok(matchingService.updateStatus(firstUserId, secondUserId, status));
	}

	@GetMapping("/getRandomUser/{loginUserId}/{minAge}/{maxAge}/{maxDistance}")
	public ResponseEntity<UserDTO> getRandomUser(@PathVariable("loginUserId") Integer loginUserId,
			@PathVariable("minAge") int minAge, @PathVariable("maxAge") int maxAge,
			@PathVariable("maxDistance") int maxDistance) {
		User user = userService.getRandomUser(loginUserId, minAge, maxAge, maxDistance);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);

		return ResponseEntity.ok(userDTO);
	}

	@GetMapping("/getPurposeUser/{loginUserId}/{minAge}/{maxAge}/{maxDistance}/{purposeName}")
	public ResponseEntity<UserDTO> getPurposeUser(@PathVariable("loginUserId") Integer loginUserId,
			@PathVariable("minAge") int minAge, @PathVariable("maxAge") int maxAge,
			@PathVariable("maxDistance") int maxDistance, @PathVariable String purposeName) {

		User user = userService.getRandomPurposeUser(loginUserId, minAge, maxAge, maxDistance, purposeName);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return ResponseEntity.ok(userDTO);
	}

	@GetMapping("/getMatchingUser/{loginId}")
	public ResponseEntity<List<UserDTO>> getMatchingUser(@PathVariable int loginId) {

		List<User> user = matchingService.getMatchingUser(loginId);
		List<UserDTO> userDTO = user.stream().map(element -> modelMapper.map(element, UserDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(userDTO);
	}

	@GetMapping("/getBlog")
	public ResponseEntity<List<BlogDTO>> getAllBlogs() {
		List<Blog> blog = blogService.getAllBlogsPublish();

		List<BlogDTO> blogDTO = blog.stream().map(element -> modelMapper.map(element, BlogDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(blogDTO);
	}

	@GetMapping("/getReason")
	public ResponseEntity<List<ReasonDTO>> getAllReason() {
		List<Reason> blog = reasonService.getAllReasons();

		List<ReasonDTO> blogDTO = blog.stream().map(element -> modelMapper.map(element, ReasonDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(blogDTO);
	}

	@GetMapping("/getBlock/{id}")
	public ResponseEntity<List<BlockDTO>> getByUserByUseId_Id(@PathVariable int id) {
		List<Block> blog = blockService.getByUserByUseId_Id(id);

		List<BlockDTO> blogDTO = blog.stream().map(element -> modelMapper.map(element, BlockDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(blogDTO);
	}

	@GetMapping("/block/{id}/{blockedId}/{status}")
	public ResponseEntity<List<BlockDTO>> blockUser(@PathVariable int id, @PathVariable int blockedId,
			@PathVariable int status) {
		// case not matching yet->create a block
		blockService.saveBlock(new Block(userService.getUserById(blockedId).get(), userService.getUserById(id).get()));
		// create a block
		// case matching-> change status
		// matchingService.updateStatus(id, blockedId, status);
		matchingService.updateBlockStatus(blockedId, id, status);
		List<Block> blog = blockService.getByUserByUseId_Id(id);

		List<BlockDTO> blogDTO = blog.stream().map(element -> modelMapper.map(element, BlockDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(blogDTO);
	}

	@GetMapping("/getReport/{id}")
	public ResponseEntity<List<ReportDTO>> getByUserByReporterId_Id(@PathVariable int id) {
		List<Report> blog = reportService.getByUserByReporterId_Id(id);

		List<ReportDTO> blogDTO = blog.stream().map(element -> modelMapper.map(element, ReportDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(blogDTO);
	}

	@GetMapping("/getChatUser/{loginId}")
	public ResponseEntity<List<UserDTO>> getChatUser(@PathVariable int loginId) {

		List<User> user = matchingService.getChatUser(loginId);
		List<UserDTO> userDTO = user.stream().map(element -> modelMapper.map(element, UserDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(userDTO);
	}

	@GetMapping("/getOnlineUser/{loginUserId}/{minAge}/{maxAge}/{maxDistance}")
	public ResponseEntity<UserDTO> getOnlineUser(@PathVariable("loginUserId") Integer loginUserId,
			@PathVariable("minAge") int minAge, @PathVariable("maxAge") int maxAge,
			@PathVariable("maxDistance") int maxDistance) {

		User user = userService.getRandomOnlineUser(loginUserId, minAge, maxAge, maxDistance, true);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return ResponseEntity.ok(userDTO);
	}

	@GetMapping("/getCurrentUser/{id}")
	public ResponseEntity<UserDTO> getCurrentUser(@PathVariable int id) {
		return ResponseEntity.ok(userService.getUser(id));
	}

	@PutMapping("/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
		String email = request.get("email");
		String newPassword = request.get("newPassword");

		User user = userService.getUserByEmail(email).orElse(null);

		if (user != null) {
			String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
			user.setPassword(encodedPassword);
			userService.saveUser(user);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	@GetMapping("/getUserPet/{loginUserId}/{minAge}/{maxAge}/{maxDistance}")
	public ResponseEntity<UserDTO> getUserPet(@PathVariable("loginUserId") Integer loginUserId,
			@PathVariable("minAge") int minAge, @PathVariable("maxAge") int maxAge,
			@PathVariable("maxDistance") int maxDistance) {
		User user = userService.getRandomUserPet(loginUserId, minAge, maxAge, maxDistance);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return ResponseEntity.ok(userDTO);
	}

	@GetMapping("/getUserExercise/{loginUserId}/{minAge}/{maxAge}/{maxDistance}")
	public ResponseEntity<UserDTO> getUserExercise(@PathVariable("loginUserId") Integer loginUserId,
			@PathVariable("minAge") int minAge, @PathVariable("maxAge") int maxAge,
			@PathVariable("maxDistance") int maxDistance) {
		User user = userService.getRandomUserExercise(loginUserId, minAge, maxAge, maxDistance);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return ResponseEntity.ok(userDTO);
	}

	@GetMapping("/getUserMusic/{loginUserId}/{minAge}/{maxAge}/{maxDistance}")
	public ResponseEntity<UserDTO> getUserMusic(@PathVariable("loginUserId") Integer loginUserId,
			@PathVariable("minAge") int minAge, @PathVariable("maxAge") int maxAge,
			@PathVariable("maxDistance") int maxDistance) {
		User user = userService.getRandomUserMusic(loginUserId, minAge, maxAge, maxDistance);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return ResponseEntity.ok(userDTO);
	}

	@PostMapping("/report/{reasonId}/{reporterId}/{reportedId}")
	public ResponseEntity<ReportDTO> report(@PathVariable("reasonId") Integer reasonId,
			@PathVariable("reporterId") Integer reporterId, @PathVariable("reportedId") Integer reportedId,
			@RequestBody ReportRequest request) {
		String description = request.getDescription();
		List<String> evidens = request.getEvidens();
		Report report = new Report("Processing", reasonService.getReasonById(reasonId).get(),
				userService.getUserById(reporterId).get(), userService.getUserById(reportedId).get(), description,
				Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		reportService.saveReport(report);
		for (String evidence : evidens) {
			Evidence newEvidence = new Evidence(report, evidence);
			evidenceService.saveEvidence(newEvidence);
		}
		matchingService.updateBlockStatus(reportedId, reporterId, -6);

		ReportDTO reportDTO = modelMapper.map(report, ReportDTO.class);
		return ResponseEntity.ok(reportDTO);
	}

	@GetMapping("/getMatchingTest")
	public ResponseEntity<List<Matching>> getMatchingTest() {

		// User user = modelMapper.map(new UserDTO(), User.class);

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
