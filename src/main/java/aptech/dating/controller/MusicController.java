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
import aptech.dating.DTO.MusicDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Music;
import aptech.dating.service.MusicService;

@RestController
@RequestMapping("/api/music")
@CrossOrigin(origins = "http://localhost:4200")
public class MusicController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final MusicService musicService;

	// Use constructor-based dependency injection
	@Autowired
	public MusicController(MusicService musicService) {
		this.musicService = musicService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<MusicDTO>> getAllMusics() {
		List<Music> music = musicService.getAllMusics();

		List<MusicDTO> musicDTO = music.stream().map(element -> modelMapper.map(element, MusicDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(musicDTO);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<MusicDTO> getMusicById(@PathVariable int id) {
		Optional<Music> music = musicService.getMusicById(id);

		MusicDTO musicDTO = modelMapper.map(music, MusicDTO.class);
		
		return musicDTO!=null?ResponseEntity.ok(musicDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Music> createMusic(@RequestBody @Validated MusicDTO musicDTO) {
		Music music = modelMapper.map(musicDTO, Music.class);
		return ResponseEntity.ok(musicService.saveMusic(music));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Music> updateMusic(@PathVariable int id, @RequestBody @Validated MusicDTO musicDTO) {
		Optional<Music> music = musicService.getMusicById(id);

	    if (music.isPresent()) {
	    	Music updateMusic = music.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(musicDTO, updateMusic);

	        // Save the updated admin
	        return ResponseEntity.ok(musicService.saveMusic(updateMusic));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteMusic(@PathVariable int id) {
		musicService.deleteMusic(id);
		return ResponseEntity.ok().build();
	}
}


