package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.UserDTO;
import aptech.dating.model.User;
import aptech.dating.repository.UserRepository;

@Service
public class UserService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final UserRepository userRepository;

    // Use constructor-based dependency injection
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    
    public UserDTO getUser(int id) { 
        User user = this.userRepository.findById(id).get(); 
        UserDTO userDto = this.modelMapper.map(user, UserDTO.class); 
        return userDto; 
    } 
}