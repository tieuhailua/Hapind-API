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
import aptech.dating.DTO.LiteracyDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Literacy;
import aptech.dating.service.LiteracyService;

@RestController
@RequestMapping("/api/literacy")
@CrossOrigin(origins = "http://localhost:4200")
public class LiteracyController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final LiteracyService literacyService;

	// Use constructor-based dependency injection
	@Autowired
	public LiteracyController(LiteracyService literacyService) {
		this.literacyService = literacyService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<LiteracyDTO>> getAllLiteracys() {
		List<Literacy> literacy = literacyService.getAllLiteracys();

		List<LiteracyDTO> literacyDTO = literacy.stream().map(element -> modelMapper.map(element, LiteracyDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(literacyDTO);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<LiteracyDTO> getLiteracyById(@PathVariable int id) {
		Optional<Literacy> literacy = literacyService.getLiteracyById(id);

		LiteracyDTO literacyDTO = modelMapper.map(literacy, LiteracyDTO.class);
		
		return literacyDTO!=null?ResponseEntity.ok(literacyDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Literacy> createLiteracy(@RequestBody @Validated LiteracyDTO literacyDTO) {
		Literacy literacy = modelMapper.map(literacyDTO, Literacy.class);
		return ResponseEntity.ok(literacyService.saveLiteracy(literacy));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Literacy> updateLiteracy(@PathVariable int id, @RequestBody @Validated LiteracyDTO literacyDTO) {
		Optional<Literacy> literacy = literacyService.getLiteracyById(id);

	    if (literacy.isPresent()) {
	    	Literacy updateLiteracy = literacy.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(literacyDTO, updateLiteracy);

	        // Save the updated admin
	        return ResponseEntity.ok(literacyService.saveLiteracy(updateLiteracy));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteLiteracy(@PathVariable int id) {
		literacyService.deleteLiteracy(id);
		return ResponseEntity.ok().build();
	}
}
