package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.UserImageDTO;
import aptech.dating.model.UserImage;
import aptech.dating.repository.UserImageRepository;

@Service
public class UserImageService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final UserImageRepository userImageRepository;

    // Use constructor-based dependency injection
    @Autowired
    public UserImageService(UserImageRepository userImageRepository) {
        this.userImageRepository = userImageRepository;
    }

    public List<UserImage> getAllUserImages() {
        return userImageRepository.findAll();
    }

    public Optional<UserImage> getUserImageById(int id) {
        return userImageRepository.findById(id);
    }
    
    public List<String> getImagePathsByUserId(Integer userId) {
        return userImageRepository.findImagePathsByUserId(userId);
    }
    
    public UserImage saveUserImage(UserImage userImage) {
        return userImageRepository.save(userImage);
    }

    public void deleteUserImage(int id) {
        userImageRepository.deleteById(id);
    }
    
    public UserImageDTO getUserImage(int id) { 
        UserImage userImage = this.userImageRepository.findById(id).get(); 
        UserImageDTO userImageDto = this.modelMapper.map(userImage, UserImageDTO.class); 
        return userImageDto; 
    } 
}