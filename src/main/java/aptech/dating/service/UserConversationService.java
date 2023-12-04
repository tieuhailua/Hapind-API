package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.UserConversationDTO;
import aptech.dating.model.UserConversation;
import aptech.dating.repository.UserConversationRepository;

@Service
public class UserConversationService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final UserConversationRepository userConversationRepository;

    // Use constructor-based dependency injection
    @Autowired
    public UserConversationService(UserConversationRepository userConversationRepository) {
        this.userConversationRepository = userConversationRepository;
    }

    public List<UserConversation> getAllUserConversations() {
        return userConversationRepository.findAll();
    }

    public Optional<UserConversation> getUserConversationById(int id) {
        return userConversationRepository.findById(id);
    }

    public UserConversation saveUserConversation(UserConversation userConversation) {
        return userConversationRepository.save(userConversation);
    }

    public void deleteUserConversation(int id) {
        userConversationRepository.deleteById(id);
    }
    
    public UserConversationDTO getUserConversation(int id) { 
        UserConversation userConversation = this.userConversationRepository.findById(id).get(); 
        UserConversationDTO ustConversationDto = this.modelMapper.map(userConversation, UserConversationDTO.class); 
        return ustConversationDto; 
    } 
}