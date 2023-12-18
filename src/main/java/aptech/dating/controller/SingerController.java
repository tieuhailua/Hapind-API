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
import aptech.dating.DTO.SingerDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Singer;
import aptech.dating.service.SingerService;

@RestController
@RequestMapping("/api/singer")
@CrossOrigin(origins = "http://localhost:4200")
public class SingerController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final SingerService singerService;

	// Use constructor-based dependency injection
	@Autowired
	public SingerController(SingerService singerService) {
		this.singerService = singerService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<SingerDTO>> getAllSingers() {
		List<Singer> singer = singerService.getAllSingers();

		List<SingerDTO> singerDTO = singer.stream().map(element -> modelMapper.map(element, SingerDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(singerDTO);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<SingerDTO> getSingerById(@PathVariable int id) {
		Optional<Singer> singer = singerService.getSingerById(id);

		SingerDTO singerDTO = modelMapper.map(singer, SingerDTO.class);
		
		return singerDTO!=null?ResponseEntity.ok(singerDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Singer> createSinger(@RequestBody @Validated SingerDTO singerDTO) {
		Singer singer = modelMapper.map(singerDTO, Singer.class);
		return ResponseEntity.ok(singerService.saveSinger(singer));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Singer> updateSinger(@PathVariable int id, @RequestBody @Validated SingerDTO singerDTO) {
		Optional<Singer> singer = singerService.getSingerById(id);

	    if (singer.isPresent()) {
	    	Singer updateSinger = singer.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(singerDTO, updateSinger);

	        // Save the updated admin
	        return ResponseEntity.ok(singerService.saveSinger(updateSinger));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteSinger(@PathVariable int id) {
		singerService.deleteSinger(id);
		return ResponseEntity.ok().build();
	}
}