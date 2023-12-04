package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.ConversationDTO;
import aptech.dating.model.Conversation;
import aptech.dating.repository.ConversationRepository;

@Service
public class ConversationService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final ConversationRepository conversationRepository;

    // Use constructor-based dependency injection
    @Autowired
    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public List<Conversation> getAllConversations() {
        return conversationRepository.findAll();
    }

    public Optional<Conversation> getConversationById(int id) {
        return conversationRepository.findById(id);
    }

    public Conversation saveConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public void deleteConversation(int id) {
    	conversationRepository.deleteById(id);
    }
    
    public ConversationDTO getConversation(int id) { 
        Conversation conversation = this.conversationRepository.findById(id).get(); 
        ConversationDTO conversationDto = this.modelMapper.map(conversation, ConversationDTO.class); 
        return conversationDto; 
    } 
}

