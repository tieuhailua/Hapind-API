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

import aptech.dating.DTO.ConversationDTO;
import aptech.dating.DTO.FamilyDTO;
import aptech.dating.model.Banned;
import aptech.dating.model.Conversation;
import aptech.dating.model.Family;
import aptech.dating.service.ConversationService;

@RestController
@RequestMapping("/api/conversation")
@CrossOrigin(origins = "http://localhost:4200")
public class ConversationController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final ConversationService conversationService;

	// Use constructor-based dependency injection
	@Autowired
	public ConversationController(ConversationService conversationService) {
		this.conversationService = conversationService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<ConversationDTO>> getAllConversations() {
		List<Conversation> conversation = conversationService.getAllConversations();

		List<ConversationDTO> conversationDTO = conversation.stream().map(element -> modelMapper.map(element, ConversationDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(conversationDTO);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<ConversationDTO> getConversationById(@PathVariable int id) {
		Optional<Conversation> conversation = conversationService.getConversationById(id);

		ConversationDTO conversationDTO = modelMapper.map(conversation, ConversationDTO.class);
		
		return conversationDTO!=null?ResponseEntity.ok(conversationDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Conversation> createConversation(@RequestBody @Validated ConversationDTO conversationDTO) {
		Conversation conversation = modelMapper.map(conversationDTO, Conversation.class);
		return ResponseEntity.ok(conversationService.saveConversation(conversation));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Conversation> updateConversation(@PathVariable int id, @RequestBody @Validated ConversationDTO conversationDTO) {
		Optional<Conversation> conversation = conversationService.getConversationById(id);

	    if (conversation.isPresent()) {
	    	Conversation updateConversation = conversation.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(conversationDTO, updateConversation);

	        // Save the updated admin
	        return ResponseEntity.ok(conversationService.saveConversation(updateConversation));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteConversation(@PathVariable int id) {
		conversationService.deleteConversation(id);
		return ResponseEntity.ok().build();
	}
}


