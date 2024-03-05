package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import aptech.dating.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.BannedDTO;
import aptech.dating.DTO.PurposeDTO;
import aptech.dating.model.Banned;
import aptech.dating.model.Purpose;
import aptech.dating.repository.PurposeRepository;

@Service
public class PurposeService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
	private final PurposeRepository purposeRepository;

	// Use constructor-based dependency injection
	@Autowired
	public PurposeService(PurposeRepository purposeRepository) {
		this.purposeRepository = purposeRepository;
	}

	public List<Purpose> getAllPurposes() {
		return purposeRepository.findAll();
	}

	public Optional<Purpose> getPurposeById(int id) {
		return purposeRepository.findById(id);
	}

	public Purpose getPurposeByUserId(User user) {
		return purposeRepository.findPurposeByUsers(user);
	}
	
	public Purpose getPurposeByName(String name) {
		return purposeRepository.findPurposeByName(name);
	}

	public Purpose savePurpose(Purpose purpose) {
		return purposeRepository.save(purpose);
	}

	public void deletePurpose(int id) {
		purposeRepository.deleteById(id);
	}
	
	public PurposeDTO getPurpose(int id) { 
        Purpose purpose = this.purposeRepository.findById(id).get(); 
        PurposeDTO purposeDto = this.modelMapper.map(purpose, PurposeDTO.class); 
        return purposeDto; 
    } 
}