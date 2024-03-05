package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.UserHobbyDTO;
import aptech.dating.model.User;
import aptech.dating.model.UserHobby;
import aptech.dating.model.UserMusic;
import aptech.dating.repository.UserHobbyRepository;

@Service
public class UserHobbyService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final UserHobbyRepository userHobbyRepository;

    // Use constructor-based dependency injection
    @Autowired
    public UserHobbyService(UserHobbyRepository userHobbyRepository) {
        this.userHobbyRepository = userHobbyRepository;
    }

    public List<UserHobby> getAllUserHobbys() {
        return userHobbyRepository.findAll();
    }

    public List<UserHobby> getUserHobbiesByUserId(int userId) {
        return userHobbyRepository.findAllByUserId(userId);
    }
    
    public Optional<UserHobby> getUserHobbyById(int id) {
        return userHobbyRepository.findById(id);
    }
    
    public List<UserHobby> getChooseUserHobbysByUserId(int userId) {
        return userHobbyRepository.findAllByUserIdAndChooseIsTrue(userId);
    }
    
    public UserHobby getUserHobbyByUserAndHobbyName(User user, String hobbyName) {
		return userHobbyRepository.findUserHobbyByUserAndHobby_Name(user,hobbyName);
	}
    
    public UserHobby saveUserHobby(UserHobby userHobby) {
        return userHobbyRepository.save(userHobby);
    }

    public void deleteUserHobby(int id) {
        userHobbyRepository.deleteById(id);
    }
    
    public UserHobbyDTO getUserHobby(int id) { 
        UserHobby userHobby = this.userHobbyRepository.findById(id).get(); 
        UserHobbyDTO userHobbyDto = this.modelMapper.map(userHobby, UserHobbyDTO.class); 
        return userHobbyDto; 
    } 
}