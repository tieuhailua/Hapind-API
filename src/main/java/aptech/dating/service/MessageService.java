package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.MessageDTO;
import aptech.dating.model.Message;
import aptech.dating.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final MessageRepository messageRepository;

    // Use constructor-based dependency injection
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(int id) {
        return messageRepository.findById(id);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(int id) {
    	messageRepository.deleteById(id);
    }
    
    public MessageDTO getMessage(int id) { 
        Message message = this.messageRepository.findById(id).get(); 
        MessageDTO messageDto = this.modelMapper.map(message, MessageDTO.class); 
        return messageDto; 
    } 
}

