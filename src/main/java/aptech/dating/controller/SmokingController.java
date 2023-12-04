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
import aptech.dating.DTO.SmokingDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Smoking;
import aptech.dating.service.SmokingService;

@RestController
@RequestMapping("/api/smoking")
public class SmokingController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final SmokingService smokingService;

	// Use constructor-based dependency injection
	@Autowired
	public SmokingController(SmokingService smokingService) {
		this.smokingService = smokingService;
	}

	@GetMapping
	public ResponseEntity<List<SmokingDTO>> getAllSmokings() {
		List<Smoking> smoking = smokingService.getAllSmokings();

		List<SmokingDTO> smokingDTO = smoking.stream().map(element -> modelMapper.map(element, SmokingDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(smokingDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SmokingDTO> getSmokingById(@PathVariable int id) {
		Optional<Smoking> smoking = smokingService.getSmokingById(id);

		SmokingDTO smokingDTO = modelMapper.map(smoking, SmokingDTO.class);
		
		return smokingDTO!=null?ResponseEntity.ok(smokingDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Smoking> createSmoking(@RequestBody @Validated SmokingDTO smokingDTO) {
		Smoking smoking = modelMapper.map(smokingDTO, Smoking.class);
		return ResponseEntity.ok(smokingService.saveSmoking(smoking));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Smoking> updateSmoking(@PathVariable int id, @RequestBody @Validated SmokingDTO smokingDTO) {
		Optional<Smoking> smoking = smokingService.getSmokingById(id);

	    if (smoking.isPresent()) {
	    	Smoking updateSmoking = smoking.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(smokingDTO, updateSmoking);

	        // Save the updated admin
	        return ResponseEntity.ok(smokingService.saveSmoking(updateSmoking));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSmoking(@PathVariable int id) {
		smokingService.deleteSmoking(id);
		return ResponseEntity.ok().build();
	}
}