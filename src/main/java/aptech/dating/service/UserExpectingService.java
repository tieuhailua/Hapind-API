package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.UserExpectingDTO;
import aptech.dating.model.UserExpecting;
import aptech.dating.repository.UserExpectingRepository;

@Service
public class UserExpectingService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final UserExpectingRepository userExpectingRepository;

    // Use constructor-based dependency injection
    @Autowired
    public UserExpectingService(UserExpectingRepository userExpectingRepository) {
        this.userExpectingRepository = userExpectingRepository;
    }

    public List<UserExpecting> getAllUserExpectings() {
        return userExpectingRepository.findAll();
    }

    public Optional<UserExpecting> getUserExpectingById(int id) {
        return userExpectingRepository.findById(id);
    }

    public UserExpecting saveUserExpecting(UserExpecting userExpecting) {
        return userExpectingRepository.save(userExpecting);
    }

    public void deleteUserExpecting(int id) {
        userExpectingRepository.deleteById(id);
    }
    
    public UserExpectingDTO getUserExpecting(int id) { 
        UserExpecting userExpecting = this.userExpectingRepository.findById(id).get(); 
        UserExpectingDTO userExpectingDto = this.modelMapper.map(userExpecting, UserExpectingDTO.class); 
        return userExpectingDto; 
    } 
}