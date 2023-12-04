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
import aptech.dating.DTO.VideoCallDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.VideoCall;
import aptech.dating.service.VideoCallService;

@RestController
@RequestMapping("/api/videoCall")
public class VideoCallController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final VideoCallService videoCallService;

	// Use constructor-based dependency injection
	@Autowired
	public VideoCallController(VideoCallService videoCallService) {
		this.videoCallService = videoCallService;
	}

	@GetMapping
	public ResponseEntity<List<VideoCallDTO>> getAllVideoCalls() {
		List<VideoCall> videoCall = videoCallService.getAllVideoCalls();

		List<VideoCallDTO> videoCallDTO = videoCall.stream().map(element -> modelMapper.map(element, VideoCallDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(videoCallDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VideoCallDTO> getVideoCallById(@PathVariable int id) {
		Optional<VideoCall> videoCall = videoCallService.getVideoCallById(id);

		VideoCallDTO videoCallDTO = modelMapper.map(videoCall, VideoCallDTO.class);
		
		return videoCallDTO!=null?ResponseEntity.ok(videoCallDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<VideoCall> createVideoCall(@RequestBody @Validated VideoCallDTO videoCallDTO) {
		VideoCall videoCall = modelMapper.map(videoCallDTO, VideoCall.class);
		return ResponseEntity.ok(videoCallService.saveVideoCall(videoCall));
	}

	@PutMapping("/{id}")
	public ResponseEntity<VideoCall> updateVideoCall(@PathVariable int id, @RequestBody @Validated VideoCallDTO videoCallDTO) {
		Optional<VideoCall> videoCall = videoCallService.getVideoCallById(id);

	    if (videoCall.isPresent()) {
	    	VideoCall updateVideoCall = videoCall.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(videoCallDTO, updateVideoCall);

	        // Save the updated admin
	        return ResponseEntity.ok(videoCallService.saveVideoCall(updateVideoCall));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVideoCall(@PathVariable int id) {
		videoCallService.deleteVideoCall(id);
		return ResponseEntity.ok().build();
	}
}