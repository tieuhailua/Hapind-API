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

import aptech.dating.DTO.ExpectingDTO;
import aptech.dating.DTO.FamilyDTO;
import aptech.dating.model.Banned;
import aptech.dating.model.Drinking;
import aptech.dating.model.Expecting;
import aptech.dating.model.Family;
import aptech.dating.service.ExpectingService;

@RestController
@RequestMapping("/api/expecting")
@CrossOrigin(origins = "http://localhost:4200")
public class ExpectingController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final ExpectingService expectingService;

	// Use constructor-based dependency injection
	@Autowired
	public ExpectingController(ExpectingService expectingService) {
		this.expectingService = expectingService;
	}

	@GetMapping
	public ResponseEntity<List<ExpectingDTO>> getAllExpectings() {
		List<Expecting> expecting = expectingService.getAllExpectings();

		List<ExpectingDTO> expectingDTO = expecting.stream().map(element -> modelMapper.map(element, ExpectingDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(expectingDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExpectingDTO> getExpectingById(@PathVariable int id) {
		Optional<Expecting> expecting = expectingService.getExpectingById(id);

		ExpectingDTO expectingDTO = modelMapper.map(expecting, ExpectingDTO.class);
		
		return expectingDTO!=null?ResponseEntity.ok(expectingDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Expecting> createExpecting(@RequestBody @Validated ExpectingDTO expectingDTO) {
		Expecting expecting = modelMapper.map(expectingDTO, Expecting.class);
		return ResponseEntity.ok(expectingService.saveExpecting(expecting));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Expecting> updateExpecting(@PathVariable int id, @RequestBody @Validated ExpectingDTO expectingDTO) {
		Optional<Expecting> expecting = expectingService.getExpectingById(id);

	    if (expecting.isPresent()) {
	    	Expecting updateExpecting = expecting.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(expectingDTO, updateExpecting);

	        // Save the updated admin
	        return ResponseEntity.ok(expectingService.saveExpecting(updateExpecting));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteExpecting(@PathVariable int id) {
		expectingService.deleteExpecting(id);
		return ResponseEntity.ok().build();
	}
}






