package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import aptech.dating.DTO.BannedDTO;
import aptech.dating.DTO.FamilyDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.User;
import aptech.dating.service.AdminService;
import aptech.dating.service.BannedService;
import aptech.dating.service.UserService;

@RestController
@RequestMapping("/api/banned")
@CrossOrigin(origins = "http://localhost:4200")
public class BannedController {
	@Autowired
	private ModelMapper modelMapper;
	
	// Declare the service as final to ensure its immutability
	private final BannedService bannedService;
	private final UserService userService;
	private final AdminService adminService;
	// Use constructor-based dependency injection
	@Autowired
	public BannedController(BannedService bannedService, UserService userService, AdminService adminService) {
		this.bannedService = bannedService;
		this.userService = userService;
		this.adminService = adminService;
	}

	@GetMapping
	public ResponseEntity<List<BannedDTO>> getAllBanneds() {
		List<Banned> banned = bannedService.getAllBanneds();

		List<BannedDTO> bannnedDTO = banned.stream().map(element -> modelMapper.map(element, BannedDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(bannnedDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BannedDTO> getBannedById(@PathVariable int id) {
		Optional<Banned> banned = bannedService.getBannedById(id);

		BannedDTO bannedDTO = modelMapper.map(banned, BannedDTO.class);
		
		return bannedDTO!=null?ResponseEntity.ok(bannedDTO):ResponseEntity.notFound().build();
	}

	
	@GetMapping("/ban")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Banned> createBanned(@RequestParam int adminId, int userId) {
		BannedDTO bannedDTO = new BannedDTO();
		Banned banned = modelMapper.map(bannedDTO, Banned.class);
		banned.setAdmin(adminService.getAdminById(adminId).get());
		banned.setUser(userService.getUserById(userId).get());
		return ResponseEntity.ok(bannedService.saveBanned(banned));
	}

//	@PostMapping
//	public ResponseEntity<Banned> addUserBanned(@RequestBody @Validated BannedDTO bannedDTO, int userId,  int adminId) {
//		Banned banned = modelMapper.map(bannedDTO, Banned.class);
//		banned.setAdmin(adminService.getAdminById(adminId).get());
//		banned.setUser(userService.getUserById(userId).get());
//		return ResponseEntity.ok(bannedService.saveBanned(banned));
//	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Banned> updateBanned(@PathVariable int id, @RequestBody @Validated BannedDTO bannedDTO) {
		Optional<Banned> banned = bannedService.getBannedById(id);

	    if (banned.isPresent()) {
	    	Banned updateBanned = banned.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(bannedDTO, updateBanned);

	        // Save the updated admin
	        return ResponseEntity.ok(bannedService.saveBanned(updateBanned));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBanned(@PathVariable int id) {
		bannedService.deleteBanned(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/get/{id}") 
    public ResponseEntity<BannedDTO> getBanned(@PathVariable("id") int id){ 
        BannedDTO bannedDto = this.bannedService.getBanned(id); 
        return new ResponseEntity<BannedDTO>(bannedDto, HttpStatus.OK); 
    } 
}