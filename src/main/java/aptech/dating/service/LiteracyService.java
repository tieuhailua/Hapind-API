package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import aptech.dating.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.LiteracyDTO;
import aptech.dating.model.Literacy;
import aptech.dating.repository.LiteracyRepository;

@Service
public class LiteracyService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final LiteracyRepository literacyRepository;

    // Use constructor-based dependency injection
    @Autowired
    public LiteracyService(LiteracyRepository literacyRepository) {
        this.literacyRepository = literacyRepository;
    }

    public List<Literacy> getAllLiteracys() {
        return literacyRepository.findAll();
    }

    public Optional<Literacy> getLiteracyById(int id) {
        return literacyRepository.findById(id);
    }

    public Literacy getLiteracyByUserId(User user) {
        return literacyRepository.findLiteracyByUsers(user);
    }

    public Literacy saveLiteracy(Literacy literacy) {
        return literacyRepository.save(literacy);
    }

    public void deleteLiteracy(int id) {
    	literacyRepository.deleteById(id);
    }
    
    public LiteracyDTO getLiteracy(int id) { 
        Literacy literacy = this.literacyRepository.findById(id).get(); 
        LiteracyDTO literacyDto = this.modelMapper.map(literacy, LiteracyDTO.class); 
        return literacyDto; 
    } 
}










