package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.HobbyDTO;
import aptech.dating.model.Expecting;
import aptech.dating.model.Hobby;
import aptech.dating.repository.HobbyRepository;

@Service
public class HobbyService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final HobbyRepository hobbyRepository;

    // Use constructor-based dependency injection
    @Autowired
    public HobbyService(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }

    public List<Hobby> getAllHobbys() {
        return hobbyRepository.findAll();
    }

    public Optional<Hobby> getHobbyById(int id) {
        return hobbyRepository.findById(id);
    }
    
    public Hobby getHobbyByName(String name) {
		return hobbyRepository.findHobbyByName(name);
	}
    
    public Hobby saveHobby(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    public void deleteHobby(int id) {
    	hobbyRepository.deleteById(id);
    }
    
    public HobbyDTO getHobby(int id) { 
        Hobby hobby = this.hobbyRepository.findById(id).get(); 
        HobbyDTO hobbyDto = this.modelMapper.map(hobby, HobbyDTO.class); 
        return hobbyDto; 
    } 
}








