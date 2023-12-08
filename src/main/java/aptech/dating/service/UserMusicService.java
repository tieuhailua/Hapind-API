package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.UserMusicDTO;
import aptech.dating.model.UserMusic;
import aptech.dating.repository.UserMusicRepository;

@Service
public class UserMusicService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final UserMusicRepository userMusicRepository;

    // Use constructor-based dependency injection
    @Autowired
    public UserMusicService(UserMusicRepository userMusicRepository) {
        this.userMusicRepository = userMusicRepository;
    }

    public List<UserMusic> getAllUserMusics() {
        return userMusicRepository.findAll();
    }
    
    public List<UserMusic> getUserMusicsByUserId(int userId) {
        return userMusicRepository.findAllByUserId(userId);
    }
    
    public Optional<UserMusic> getUserMusicById(int id) {
        return userMusicRepository.findById(id);
    }

    public UserMusic saveUserMusic(UserMusic userMusic) {
        return userMusicRepository.save(userMusic);
    }

    public void deleteUserMusic(int id) {
        userMusicRepository.deleteById(id);
    }
    
    public UserMusicDTO getUserMusic(int id) { 
        UserMusic userMusic = this.userMusicRepository.findById(id).get(); 
        UserMusicDTO userMusicDto = this.modelMapper.map(userMusic, UserMusicDTO.class); 
        return userMusicDto; 
    } 
}