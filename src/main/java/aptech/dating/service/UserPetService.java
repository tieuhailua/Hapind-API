package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.UserPetDTO;
import aptech.dating.model.User;
import aptech.dating.model.UserMusic;
import aptech.dating.model.UserPet;
import aptech.dating.repository.UserPetRepository;

@Service
public class UserPetService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final UserPetRepository userPetRepository;

    // Use constructor-based dependency injection
    @Autowired
    public UserPetService(UserPetRepository userPetRepository) {
        this.userPetRepository = userPetRepository;
    }

    public List<UserPet> getAllUserPets() {
        return userPetRepository.findAll();
    }
    
    public List<UserPet> getUserPetsByUserId(int userId) {
        return userPetRepository.findAllByUserId(userId);
    }
    
    public List<UserPet> getChooseUserPetsByUserId(int userId) {
        return userPetRepository.findAllByUserIdAndChooseIsTrue(userId);
    }
    
    public UserPet getUserPetByUserAndPetName(User user, String petName) {
		return userPetRepository.findUserPetByUserAndPet_Name(user,petName);
	}
    
    public Optional<UserPet> getUserPetById(int id) {
        return userPetRepository.findById(id);
    }

    public UserPet saveUserPet(UserPet userPet) {
        return userPetRepository.save(userPet);
    }

    public void deleteUserPet(int id) {
        userPetRepository.deleteById(id);
    }
    
    public UserPetDTO getUserPet(int id) { 
        UserPet userPet = this.userPetRepository.findById(id).get(); 
        UserPetDTO userPetDto = this.modelMapper.map(userPet, UserPetDTO.class); 
        return userPetDto; 
    } 
}