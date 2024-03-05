package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.UserSingerDTO;
import aptech.dating.model.User;
import aptech.dating.model.UserMusic;
import aptech.dating.model.UserSinger;
import aptech.dating.repository.UserSingerRepository;

@Service
public class UserSingerService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final UserSingerRepository userSingerRepository;

    // Use constructor-based dependency injection
    @Autowired
    public UserSingerService(UserSingerRepository userSingerRepository) {
        this.userSingerRepository = userSingerRepository;
    }

    public List<UserSinger> getAllUserSingers() {
        return userSingerRepository.findAll();
    }
    
    public List<UserSinger> getUserSingersByUserId(int userId) {
        return userSingerRepository.findAllByUserIdAndChooseIsTrue(userId);
    }
    
    public UserSinger getUserSingerByUserAndSingerName(User user, String musicName) {
		return userSingerRepository.findUserSingerByUserAndSinger_Name(user,musicName);
	}

    public Optional<UserSinger> getUserSingerById(int id) {
        return userSingerRepository.findById(id);
    }

    public UserSinger saveUserSinger(UserSinger userSinger) {
        return userSingerRepository.save(userSinger);
    }

    public void deleteUserSinger(int id) {
        userSingerRepository.deleteById(id);
    }
    
    public UserSingerDTO getUserSinger(int id) { 
        UserSinger userSinger = this.userSingerRepository.findById(id).get(); 
        UserSingerDTO userSingerDto = this.modelMapper.map(userSinger, UserSingerDTO.class); 
        return userSingerDto; 
    } 
}