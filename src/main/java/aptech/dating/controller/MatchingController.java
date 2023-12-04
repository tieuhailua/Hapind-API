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
import aptech.dating.DTO.MatchingDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Matching;
import aptech.dating.service.MatchingService;

@RestController
@RequestMapping("/api/matching")
@CrossOrigin(origins = "http://localhost:4200")
public class MatchingController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final MatchingService matchingService;

	// Use constructor-based dependency injection
	@Autowired
	public MatchingController(MatchingService matchingService) {
		this.matchingService = matchingService;
	}

	@GetMapping
	public ResponseEntity<List<MatchingDTO>> getAllMatchings() {
		List<Matching> matching = matchingService.getAllMatchings();

		List<MatchingDTO> matchingDTO = matching.stream().map(element -> modelMapper.map(element, MatchingDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(matchingDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MatchingDTO> getMatchingById(@PathVariable int id) {
		Optional<Matching> matching = matchingService.getMatchingById(id);

		MatchingDTO matchingDTO = modelMapper.map(matching, MatchingDTO.class);
		
		return matchingDTO!=null?ResponseEntity.ok(matchingDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Matching> createMatching(@RequestBody @Validated MatchingDTO matchingDTO) {
		Matching matching = modelMapper.map(matchingDTO, Matching.class);
		return ResponseEntity.ok(matchingService.saveMatching(matching));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Matching> updateMatching(@PathVariable int id, @RequestBody @Validated MatchingDTO matchingDTO) {
		Optional<Matching> matching = matchingService.getMatchingById(id);

	    if (matching.isPresent()) {
	    	Matching updateMatching = matching.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(matchingDTO, updateMatching);

	        // Save the updated admin
	        return ResponseEntity.ok(matchingService.saveMatching(updateMatching));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMatching(@PathVariable int id) {
		matchingService.deleteMatching(id);
		return ResponseEntity.ok().build();
	}
}

