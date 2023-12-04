package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.LanguageDTO;
import aptech.dating.model.Language;
import aptech.dating.repository.LanguageRepository;

@Service
public class LanguageService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final LanguageRepository languageRepository;

    // Use constructor-based dependency injection
    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Optional<Language> getLanguageById(int id) {
        return languageRepository.findById(id);
    }

    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    public void deleteLanguage(int id) {
    	languageRepository.deleteById(id);
    }
    
    public LanguageDTO getLanguage(int id) { 
        Language language = this.languageRepository.findById(id).get(); 
        LanguageDTO languageDto = this.modelMapper.map(language, LanguageDTO.class); 
        return languageDto; 
    } 
}









