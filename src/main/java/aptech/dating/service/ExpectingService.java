package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.ExpectingDTO;
import aptech.dating.model.Expecting;
import aptech.dating.repository.ExpectingRepository;

@Service
public class ExpectingService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final ExpectingRepository expectingRepository;

    // Use constructor-based dependency injection
    @Autowired
    public ExpectingService(ExpectingRepository expectingRepository) {
        this.expectingRepository = expectingRepository;
    }

    public List<Expecting> getAllExpectings() {
        return expectingRepository.findAll();
    }

    public Optional<Expecting> getExpectingById(int id) {
        return expectingRepository.findById(id);
    }

    public Expecting saveExpecting(Expecting expecting) {
        return expectingRepository.save(expecting);
    }

    public void deleteExpecting(int id) {
    	expectingRepository.deleteById(id);
    }
    
    public ExpectingDTO getExpecting(int id) { 
        Expecting expecting = this.expectingRepository.findById(id).get(); 
        ExpectingDTO expectingDto = this.modelMapper.map(expecting, ExpectingDTO.class); 
        return expectingDto; 
    } 
}





