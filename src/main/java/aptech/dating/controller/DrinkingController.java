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

import aptech.dating.DTO.DrinkingDTO;
import aptech.dating.DTO.FamilyDTO;
import aptech.dating.model.Banned;
import aptech.dating.model.Drinking;
import aptech.dating.model.Family;
import aptech.dating.service.DrinkingService;

@RestController
@RequestMapping("/api/drinking")
@CrossOrigin(origins = "http://localhost:4200")
public class DrinkingController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final DrinkingService drinkingService;

	// Use constructor-based dependency injection
	@Autowired
	public DrinkingController(DrinkingService drinkingService) {
		this.drinkingService = drinkingService;
	}

	@GetMapping
	public ResponseEntity<List<DrinkingDTO>> getAllDrinkings() {
		List<Drinking> drinking = drinkingService.getAllDrinkings();

		List<DrinkingDTO> drinkingDTO = drinking.stream().map(element -> modelMapper.map(element, DrinkingDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(drinkingDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DrinkingDTO> getDrinkingById(@PathVariable int id) {
		Optional<Drinking> drinking = drinkingService.getDrinkingById(id);

		DrinkingDTO drinkingDTO = modelMapper.map(drinking, DrinkingDTO.class);
		
		return drinkingDTO!=null?ResponseEntity.ok(drinkingDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Drinking> createDrinking(@RequestBody @Validated DrinkingDTO drinkingDTO) {
		Drinking drinking = modelMapper.map(drinkingDTO, Drinking.class);
		return ResponseEntity.ok(drinkingService.saveDrinking(drinking));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Drinking> updateConversation(@PathVariable int id, @RequestBody @Validated DrinkingDTO drinkingDTO) {
		Optional<Drinking> drinking = drinkingService.getDrinkingById(id);

	    if (drinking.isPresent()) {
	    	Drinking updateDrinking = drinking.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(drinkingDTO, updateDrinking);

	        // Save the updated admin
	        return ResponseEntity.ok(drinkingService.saveDrinking(updateDrinking));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDrinking(@PathVariable int id) {
		drinkingService.deleteDrinking(id);
		return ResponseEntity.ok().build();
	}
}



