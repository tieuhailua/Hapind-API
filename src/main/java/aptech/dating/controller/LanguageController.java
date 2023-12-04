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
import aptech.dating.DTO.LanguageDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Language;
import aptech.dating.service.LanguageService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/language")
public class LanguageController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final LanguageService languageService;

	// Use constructor-based dependency injection
	@Autowired
	public LanguageController(LanguageService languageService) {
		this.languageService = languageService;
	}

	@GetMapping
	public ResponseEntity<List<LanguageDTO>> getAllLanguages() {
		List<Language> language = languageService.getAllLanguages();

		List<LanguageDTO> languageDTO = language.stream().map(element -> modelMapper.map(element, LanguageDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(languageDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LanguageDTO> getLanguageById(@PathVariable int id) {
		Optional<Language> language = languageService.getLanguageById(id);

		LanguageDTO languageDTO = modelMapper.map(language, LanguageDTO.class);
		
		return languageDTO!=null?ResponseEntity.ok(languageDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Language> createLanguage(@RequestBody @Validated LanguageDTO languageDTO) {
		Language language = modelMapper.map(languageDTO,Language.class);
		return ResponseEntity.ok(languageService.saveLanguage(language));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Language> updateLanguage(@PathVariable int id, @RequestBody @Validated LanguageDTO languageDTO) {
		Optional<Language> language = languageService.getLanguageById(id);

	    if (language.isPresent()) {
	    	Language updateLanguage = language.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(languageDTO, updateLanguage);

	        // Save the updated admin
	        return ResponseEntity.ok(languageService.saveLanguage(updateLanguage));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLanguage(@PathVariable int id) {
		languageService.deleteLanguage(id);
		return ResponseEntity.ok().build();
	}
}









