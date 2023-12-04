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

import aptech.dating.DTO.EvidenceDTO;
import aptech.dating.DTO.FamilyDTO;
import aptech.dating.model.Banned;
import aptech.dating.model.Evidence;
import aptech.dating.model.Family;
import aptech.dating.service.EvidenceService;

@RestController
@RequestMapping("/api/evidence")
public class EvidenceController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final EvidenceService evidenceService;

	// Use constructor-based dependency injection
	@Autowired
	public EvidenceController(EvidenceService evidenceService) {
		this.evidenceService = evidenceService;
	}

	@GetMapping
	public ResponseEntity<List<EvidenceDTO>> getAllEvidences() {
		List<Evidence> evidence = evidenceService.getAllEvidences();

		List<EvidenceDTO> evidenceDTO = evidence.stream().map(element -> modelMapper.map(element, EvidenceDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(evidenceDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EvidenceDTO> getEvidenceById(@PathVariable int id) {
		Optional<Evidence> evidence = evidenceService.getEvidenceById(id);

		EvidenceDTO evidenceDTO = modelMapper.map(evidence, EvidenceDTO.class);
		
		return evidenceDTO!=null?ResponseEntity.ok(evidenceDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Evidence> createEvidence(@RequestBody @Validated EvidenceDTO evidenceDTO) {
		Evidence evidence = modelMapper.map(evidenceDTO, Evidence.class);
		return ResponseEntity.ok(evidenceService.saveEvidence(evidence));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Evidence> updateEvidence(@PathVariable int id, @RequestBody @Validated EvidenceDTO evidenceDTO) {
		Optional<Evidence> evidence = evidenceService.getEvidenceById(id);

	    if (evidence.isPresent()) {
	    	Evidence updateEvidence = evidence.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(evidenceDTO, updateEvidence);

	        // Save the updated admin
	        return ResponseEntity.ok(evidenceService.saveEvidence(updateEvidence));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEvidence(@PathVariable int id) {
		evidenceService.deleteEvidence(id);
		return ResponseEntity.ok().build();
	}
}




