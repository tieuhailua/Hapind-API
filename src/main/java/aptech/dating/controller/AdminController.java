package aptech.dating.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aptech.dating.DTO.AdminDTO;
import aptech.dating.DTO.FamilyDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Family;
import aptech.dating.service.AdminService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	// Declare the service as final to ensure its immutability
	private final AdminService adminService;

	@Autowired
	private ModelMapper modelMapper;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// Use constructor-based dependency injection
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<AdminDTO>> getAllAdmins() {
		List<Admin> admin = adminService.getByRoleMod();

		List<AdminDTO> adminDTO = admin.stream().map(element -> modelMapper.map(element, AdminDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(adminDTO);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<AdminDTO> getAdminById(@PathVariable int id) {
		Optional<Admin> admin = adminService.getAdminById(id);

		AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
		
		return adminDTO!=null?ResponseEntity.ok(adminDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Admin> createAdmin(@RequestBody @Validated AdminDTO adminDTO) {
		Admin admin = modelMapper.map(adminDTO, Admin.class);
		return ResponseEntity.ok(adminService.saveAdmin(admin));
	}
	
//	@PutMapping("/resetPassword/{modId}")
//	@PreAuthorize("hasAuthority('admin')")
//	public ResponseEntity<Admin> resetPassword(@PathVariable int modId) {
//		Optional<Admin> existingAdminOptional = adminService.getAdminById(modId);
//
//		if (existingAdminOptional == null) {
//			return ResponseEntity.notFound().build();
//		}
//		String encodedPassword = bCryptPasswordEncoder.encode("Aa@123456");
//		existingAdminOptional.get().setPassword(encodedPassword);
//		
//		return ResponseEntity.ok(adminService.saveAdmin(existingAdminOptional.get()));
//	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Admin> updateAdmin(@PathVariable Integer id, @RequestBody @Validated AdminDTO adminDTO) {
	    Optional<Admin> admin = adminService.getAdminById(id);

	    if (admin.isPresent()) {
	        Admin updateAdmin = admin.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(adminDTO, updateAdmin);

	        // Save the updated admin
	        return ResponseEntity.ok(adminService.saveAdmin(updateAdmin));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
		adminService.deleteAdmin(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<AdminDTO> getAdmin(@PathVariable("id") int id) {
		AdminDTO adminDto = this.adminService.getAdmin(id);
		return new ResponseEntity<AdminDTO>(adminDto, HttpStatus.OK);
	}
}