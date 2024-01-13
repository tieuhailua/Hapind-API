package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import aptech.dating.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.BannedDTO;
import aptech.dating.DTO.FamilyDTO;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.repository.FamilyRepository;

@Service
public class FamilyService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final FamilyRepository familyRepository;

    // Use constructor-based dependency injection
    @Autowired
    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public List<Family> getAllFamilys() {
        return familyRepository.findAll();
    }

    public Optional<Family> getFamilyById(int id) {
        return familyRepository.findById(id);
    }

    public Family getFamilyByUserId(User user) {
        return familyRepository.findFamilyByUsers(user);
    }

    public Family saveFamily(Family family) {
        return familyRepository.save(family);
    }

    public void deleteFamily(int id) {
    	familyRepository.deleteById(id);
    }
    
    public FamilyDTO getFamily(int id) { 
        Family family = this.familyRepository.findById(id).get(); 
        FamilyDTO familyDto = this.modelMapper.map(family, FamilyDTO.class); 
        return familyDto; 
    } 
}






