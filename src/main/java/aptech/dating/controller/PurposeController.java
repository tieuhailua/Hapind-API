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
import aptech.dating.DTO.PurposeDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Purpose;
import aptech.dating.service.PurposeService;

@RestController
@RequestMapping("/api/purpose")
public class PurposeController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final PurposeService purposeService;

	// Use constructor-based dependency injection
	@Autowired
	public PurposeController(PurposeService purposeService) {
		this.purposeService = purposeService;
	}

	@GetMapping
	public ResponseEntity<List<PurposeDTO>> getAllPurposes() {
		List<Purpose> purpose = purposeService.getAllPurposes();

		List<PurposeDTO> purposeDTO = purpose.stream().map(element -> modelMapper.map(element, PurposeDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(purposeDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PurposeDTO> getPurposeById(@PathVariable int id) {
		Optional<Purpose> purpose = purposeService.getPurposeById(id);

		PurposeDTO purposeDTO = modelMapper.map(purpose, PurposeDTO.class);
		
		return purposeDTO!=null?ResponseEntity.ok(purposeDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Purpose> createPurpose(@RequestBody @Validated PurposeDTO purposeDTO) {
		Purpose purpose = modelMapper.map(purposeDTO, Purpose.class);
		return ResponseEntity.ok(purposeService.savePurpose(purpose));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Purpose> updatePurpose(@PathVariable int id, @RequestBody @Validated PurposeDTO purposeDTO) {
		Optional<Purpose> purpose = purposeService.getPurposeById(id);

	    if (purpose.isPresent()) {
	    	Purpose updatePurpose = purpose.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(purposeDTO, updatePurpose);

	        // Save the updated admin
	        return ResponseEntity.ok(purposeService.savePurpose(updatePurpose));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePurpose(@PathVariable int id) {
		purposeService.deletePurpose(id);
		return ResponseEntity.ok().build();
	}
}