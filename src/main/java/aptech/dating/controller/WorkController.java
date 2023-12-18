package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import aptech.dating.DTO.WorkDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Work;
import aptech.dating.service.WorkService;

@RestController
@RequestMapping("/api/work")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final WorkService workService;

	// Use constructor-based dependency injection
	@Autowired
	public WorkController(WorkService workService) {
		this.workService = workService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<WorkDTO>> getAllWorks() {
		List<Work> work = workService.getAllWorks();

		List<WorkDTO> workDTO = work.stream().map(element -> modelMapper.map(element, WorkDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(workDTO);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<WorkDTO> getWorkById(@PathVariable int id) {
		Optional<Work> work = workService.getWorkById(id);

		WorkDTO workDTO = modelMapper.map(work, WorkDTO.class);
		
		return workDTO!=null?ResponseEntity.ok(workDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Work> createWork(@RequestBody @Validated WorkDTO workDTO) {
		Work work = modelMapper.map(workDTO, Work.class);
		return ResponseEntity.ok(workService.saveWork(work));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Work> updateWork(@PathVariable int id, @RequestBody @Validated WorkDTO workDTO) {
		Optional<Work> work = workService.getWorkById(id);

	    if (work.isPresent()) {
	    	Work updateWork = work.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(workDTO, updateWork);

	        // Save the updated admin
	        return ResponseEntity.ok(workService.saveWork(updateWork));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteWork(@PathVariable int id) {
		workService.deleteWork(id);
		return ResponseEntity.ok().build();
	}
}