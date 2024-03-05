package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.UserImageDTO;
import aptech.dating.model.UserImage;
import aptech.dating.repository.UserImageRepository;
import jakarta.transaction.Transactional;

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
    
    @Transactional
    public UserImage addOrUpdateUserImage(UserImage userImage) {
        UserImage existingImage = userImageRepository.findByUserAndId(userImage.getUser(), userImage.getId());

        if (existingImage != null) {
            // Update existing image
            existingImage.setImagePath(userImage.getImagePath());
        } else {
            // Check if the user has fewer than six images
            long imageCount = userImageRepository.countByUser(userImage.getUser());

            if (imageCount < 6) {
                // Add a new image
                userImageRepository.save(userImage);
            } else {
                // User already has six images, update at the specified index
                userImageRepository.updateImagePath(userImage.getUser(), userImage.getId(), userImage.getImagePath());
            }
        }
		return existingImage;
    }
    
    public Optional<UserImage> getUserImageById(int id) {
        return userImageRepository.findById(id);
    }
    
    public List<UserImage> getImagePathsByUserId(Integer userId) {
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