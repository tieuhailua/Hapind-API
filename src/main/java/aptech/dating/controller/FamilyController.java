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
import aptech.dating.model.Banned;
import aptech.dating.model.Expecting;
import aptech.dating.model.Family;
import aptech.dating.service.FamilyService;

@RestController
@RequestMapping("/api/family")
@CrossOrigin(origins = "http://localhost:4200")
public class FamilyController {
    @Autowired
    private ModelMapper modelMapper;
    // Declare the service as final to ensure its immutability
    private final FamilyService familyService;

    // Use constructor-based dependency injection
    @Autowired
    public FamilyController(FamilyService familyService) {
	this.familyService = familyService;
    }

    @GetMapping
    public ResponseEntity<List<FamilyDTO>> getAllFamilys() {
	List<Family> family = familyService.getAllFamilys();

	List<FamilyDTO> familyDto = family.stream().map(element -> modelMapper.map(element, FamilyDTO.class))
			.collect(Collectors.toList());

	return ResponseEntity.ok(familyDto);
    }

    

    @GetMapping("/{id}")
    public ResponseEntity<FamilyDTO> getFamilyById(@PathVariable int id) {
	Optional<Family> family = familyService.getFamilyById(id);

	FamilyDTO familyDto = modelMapper.map(family, FamilyDTO.class);
	
	return familyDto!=null?ResponseEntity.ok(familyDto):ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Family> createFamily(@RequestBody @Validated FamilyDTO familyDTO) {
	Family family = modelMapper.map(familyDTO, Family.class);
	return ResponseEntity.ok(familyService.saveFamily(family));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Family> updateFamily(@PathVariable int id, @RequestBody @Validated FamilyDTO familyDTO) {
	Optional<Family> family = familyService.getFamilyById(id);

	if (family.isPresent()) {
	    Family updateFamily = family.get();

	    modelMapper.map(familyDTO, updateFamily);

	    return ResponseEntity.ok(familyService.saveFamily(updateFamily));
	} else {
	    return ResponseEntity.notFound().build();
	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFamily(@PathVariable int id) {
	familyService.deleteFamily(id);
	return ResponseEntity.ok().build();
    }
}
