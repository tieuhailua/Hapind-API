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
import aptech.dating.DTO.MessageDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Message;
import aptech.dating.service.MessageService;

@RestController
@RequestMapping("/api/message")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final MessageService messageService;

	// Use constructor-based dependency injection
	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@GetMapping
	public ResponseEntity<List<MessageDTO>> getAllMessages() {
		List<Message> message = messageService.getAllMessages();

		List<MessageDTO> messageDTO = message.stream().map(element -> modelMapper.map(element, MessageDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(messageDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MessageDTO> getMessageById(@PathVariable int id) {
		Optional<Message> message = messageService.getMessageById(id);

		MessageDTO messageDTO = modelMapper.map(message, MessageDTO.class);
		
		return messageDTO!=null?ResponseEntity.ok(messageDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Message> createMessage(@RequestBody @Validated MessageDTO messageDTO) {
		Message message = modelMapper.map(messageDTO, Message.class);
		return ResponseEntity.ok(messageService.saveMessage(message));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody @Validated MessageDTO messageDTO) {
		Optional<Message> message = messageService.getMessageById(id);

	    if (message.isPresent()) {
	    	Message updateMessage = message.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(messageDTO, updateMessage);

	        // Save the updated admin
	        return ResponseEntity.ok(messageService.saveMessage(updateMessage));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
		messageService.deleteMessage(id);
		return ResponseEntity.ok().build();
	}
}

