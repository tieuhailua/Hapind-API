package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.UserLanguageDTO;
import aptech.dating.model.User;
import aptech.dating.model.UserLanguage;
import aptech.dating.model.UserMusic;
import aptech.dating.model.UserSinger;
import aptech.dating.repository.UserLanguageRepository;

@Service
public class UserLanguageService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final UserLanguageRepository userLanguageRepository;

    // Use constructor-based dependency injection
    @Autowired
    public UserLanguageService(UserLanguageRepository userLanguageRepository) {
        this.userLanguageRepository = userLanguageRepository;
    }

    public List<UserLanguage> getAllUserLanguages() {
        return userLanguageRepository.findAll();
    }
    
    public List<UserLanguage> getUserLanguagesByUserId(int userId) {
        return userLanguageRepository.findAllByUserId(userId);
    }
    
    public List<UserLanguage> getChooseUserLanguagesByUserId(int userId) {
        return userLanguageRepository.findAllByUserIdAndChooseIsTrue(userId);
    }
    
    public UserLanguage getUserLanguageByUserAndLanguageName(User user, String languageName) {
		return userLanguageRepository.findUserLanguageByUserAndLanguage_Name(user,languageName);
	}
    
    public Optional<UserLanguage> getUserLanguageById(int id) {
        return userLanguageRepository.findById(id);
    }

    public UserLanguage saveUserLanguage(UserLanguage userLanguage) {
        return userLanguageRepository.save(userLanguage);
    }

    public void deleteUserLanguage(int id) {
        userLanguageRepository.deleteById(id);
    }
    
    public UserLanguageDTO getUserLanguage(int id) { 
        UserLanguage userLanguage = this.userLanguageRepository.findById(id).get(); 
        UserLanguageDTO userLanguageDto = this.modelMapper.map(userLanguage, UserLanguageDTO.class); 
        return userLanguageDto; 
    } 
}