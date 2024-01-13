package aptech.dating.controller;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aptech.dating.DTO.UserDTO;
import aptech.dating.model.Drinking;
import aptech.dating.model.EmailDetails;
import aptech.dating.model.User;
import aptech.dating.payload.request.EmailLoginRequest;
import aptech.dating.payload.request.LoginRequest;
import aptech.dating.payload.request.SignupRequest;
import aptech.dating.payload.response.JwtResponse;
import aptech.dating.payload.response.MessageResponse;
import aptech.dating.repository.AdminRepository;
import aptech.dating.repository.UserRepository;
import aptech.dating.security.admin.JwtUtils;
import aptech.dating.security.user.UserJwtUtils;
import aptech.dating.service.AdminDetailsImpl;
import aptech.dating.service.EmailService;
import aptech.dating.service.UserDetailsImpl;
import aptech.dating.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	@Qualifier("adminAuthenticationManager")
	private AuthenticationManager adminAuthenticationManager;

	@Autowired
	@Qualifier("userAuthenticationManager")
	private AuthenticationManager userAuthenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserJwtUtils userjwtUtils;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	private ModelMapper modelMapper;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = adminAuthenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);

		AdminDetailsImpl adminDetails = (AdminDetailsImpl) authentication.getPrincipal();
		// String role = adminDetails.getAuthorities();
//    List<String> roles = adminDetails.getAuthorities().stream()
//            .map(item -> item.getAuthority())
//            .collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, adminDetails.getId(), adminDetails.getUsername(),
				adminRepository.findByUsername(loginRequest.getUsername()).get().getRole()));
	}

	@PostMapping("/signUp")
	public ResponseEntity<User> createUser(@RequestBody @Validated UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return ResponseEntity.ok(userRepository.save(user));
	}

	@PostMapping("/sendOtp/{email}")
	public ResponseEntity<String> sendOtp(@PathVariable String email) {
		Random random = new Random();
		int otp = random.nextInt(9000) + 1000;
		EmailDetails emailDetails = new EmailDetails(email, "Your OTP is: " + otp, "Hapind OTP", "");
		String status = emailService.sendSimpleMail(emailDetails);
		return ResponseEntity.ok(""+otp);
	}

	@PostMapping("/emailLogin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = userAuthenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = userjwtUtils.generateToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), "user"));
	}

	@GetMapping("/checkAccountExits/{username}")
	public ResponseEntity<?> checkAccountExits(@PathVariable String username) {
		if (username.contains("@")) {
			if (userRepository.findByEmail(username).isPresent()) {
				return ResponseEntity.badRequest().body("Email is already taken!");
			} else {
				return ResponseEntity.ok().build();
			}
		} else if (userRepository.findByPhone(username).isPresent()) {
			return ResponseEntity.badRequest().body("Phone is already taken!");
		}
		return ResponseEntity.ok().build();
	}

//  @PostMapping("/signup")
//  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//    if (adminRepository.existsByUsername(signUpRequest.getUsername())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Username is already taken!"));
//    }
//
//
//    // Create new user's account
//    User admin = new User(signUpRequest.getUsername(), 
//               encoder.encode(signUpRequest.getPassword()));
//
//    String strRoles = signUpRequest.getRole();
//    
//
//    if (strRoles == null) {
//      Admin userRole = adminRepository.findByName("MODERATOR")
//          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//      roles.add(userRole);
//    } else {
//      strRoles.forEach(role -> {
//        switch (role) {
//        case "admin":
//          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(adminRole);
//
//          break;
//        case "mod":
//          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(modRole);
//
//          break;
//        default:
//          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(userRole);
//        }
//      });
//    }
//
//    user.setRoles(roles);
//    userRepository.save(user);
//
//    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//  }
}
