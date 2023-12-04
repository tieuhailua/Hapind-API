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
import aptech.dating.DTO.HobbyDTO;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Hobby;
import aptech.dating.service.HobbyService;

@RestController
@RequestMapping("/api/hobby")
public class HobbyController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final HobbyService hobbyService;

	// Use constructor-based dependency injection
	@Autowired
	public HobbyController(HobbyService hobbyService) {
		this.hobbyService = hobbyService;
	}

	@GetMapping
	public ResponseEntity<List<HobbyDTO>> getAllHobbys() {
		List<Hobby> hobby = hobbyService.getAllHobbys();

		List<HobbyDTO> hobbyDTO = hobby.stream().map(element -> modelMapper.map(element, HobbyDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(hobbyDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<HobbyDTO> getHobbyById(@PathVariable int id) {
		Optional<Hobby> hobby = hobbyService.getHobbyById(id);

		HobbyDTO hobbyDTO = modelMapper.map(hobby, HobbyDTO.class);
		
		return hobbyDTO!=null?ResponseEntity.ok(hobbyDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Hobby> createHobby(@RequestBody @Validated HobbyDTO hobbyDTO) {
		Hobby hobby = modelMapper.map(hobbyDTO, Hobby.class);
		return ResponseEntity.ok(hobbyService.saveHobby(hobby));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Hobby> updateHobby(@PathVariable int id, @RequestBody @Validated HobbyDTO hobbyDTO) {
		Optional<Hobby> hobby = hobbyService.getHobbyById(id);

	    if (hobby.isPresent()) {
	    	Hobby updateHobby = hobby.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(hobbyDTO, updateHobby);

	        // Save the updated admin
	        return ResponseEntity.ok(hobbyService.saveHobby(updateHobby));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteHobby(@PathVariable int id) {
		hobbyService.deleteHobby(id);
		return ResponseEntity.ok().build();
	}
}








