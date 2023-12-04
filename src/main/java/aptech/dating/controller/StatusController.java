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
import aptech.dating.DTO.StatusDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Status;
import aptech.dating.service.StatusService;

@RestController
@RequestMapping("/api/status")
public class StatusController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final StatusService statusService;

	// Use constructor-based dependency injection
	@Autowired
	public StatusController(StatusService statusService) {
		this.statusService = statusService;
	}

	@GetMapping
	public ResponseEntity<List<StatusDTO>> getAllStatuss() {
		List<Status> status = statusService.getAllStatuss();

		List<StatusDTO> statusDTO = status.stream().map(element -> modelMapper.map(element, StatusDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(statusDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StatusDTO> getStatusById(@PathVariable int id) {
		Optional<Status> status = statusService.getStatusById(id);

		StatusDTO statusDTO = modelMapper.map(status, StatusDTO.class);
		
		return statusDTO!=null?ResponseEntity.ok(statusDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Status> createStatus(@RequestBody @Validated StatusDTO statusDTO) {
		Status status = modelMapper.map(statusDTO, Status.class);
		return ResponseEntity.ok(statusService.saveStatus(status));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Status> updateStatus(@PathVariable int id, @RequestBody @Validated StatusDTO statusDTO) {
		Optional<Status> status = statusService.getStatusById(id);

	    if (status.isPresent()) {
	    	Status updateStatus = status.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(statusDTO, updateStatus);

	        // Save the updated admin
	        return ResponseEntity.ok(statusService.saveStatus(updateStatus));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStatus(@PathVariable int id) {
		statusService.deleteStatus(id);
		return ResponseEntity.ok().build();
	}
}