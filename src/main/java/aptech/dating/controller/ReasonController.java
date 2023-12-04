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
import aptech.dating.DTO.ReasonDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Reason;
import aptech.dating.service.ReasonService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reason")
@CrossOrigin(origins = "http://localhost:4200")
public class ReasonController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final ReasonService reasonService;

	// Use constructor-based dependency injection
	@Autowired
	public ReasonController(ReasonService reasonService) {
		this.reasonService = reasonService;
	}

	@GetMapping
	public ResponseEntity<List<ReasonDTO>> getAllReasons() {
		List<Reason> reason = reasonService.getAllReasons();

		List<ReasonDTO> reasonDTO = reason.stream().map(element -> modelMapper.map(element, ReasonDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(reasonDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReasonDTO> getReasonById(@PathVariable int id) {
		Optional<Reason> reason = reasonService.getReasonById(id);

		ReasonDTO reasonDTO = modelMapper.map(reason, ReasonDTO.class);
		
		return reasonDTO!=null?ResponseEntity.ok(reasonDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Reason> createReason(@RequestBody @Validated ReasonDTO reasonDTO) {
		Reason reason = modelMapper.map(reasonDTO, Reason.class);
		return ResponseEntity.ok(reasonService.saveReason(reason));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Reason> updateReason(@PathVariable int id, @RequestBody @Validated ReasonDTO reasonDTO) {
		Optional<Reason> reason = reasonService.getReasonById(id);

	    if (reason.isPresent()) {
	    	Reason updateReason = reason.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(reasonDTO, updateReason);

	        // Save the updated admin
	        return ResponseEntity.ok(reasonService.saveReason(updateReason));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReason(@PathVariable int id) {
		reasonService.deleteReason(id);
		return ResponseEntity.ok().build();
	}
}