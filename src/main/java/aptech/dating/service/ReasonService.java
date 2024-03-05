package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.ReasonDTO;
import aptech.dating.model.Pet;
import aptech.dating.model.Reason;
import aptech.dating.repository.ReasonRepository;

@Service
public class ReasonService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final ReasonRepository reasonRepository;

    // Use constructor-based dependency injection
    @Autowired
    public ReasonService(ReasonRepository reasonRepository) {
        this.reasonRepository = reasonRepository;
    }
    
    public Reason getReasonByName(String name) {
		return reasonRepository.findReasonByName(name);
	}
    
    public List<Reason> getAllReasons() {
        return reasonRepository.findAll();
    }

    public Optional<Reason> getReasonById(int id) {
        return reasonRepository.findById(id);
    }

    public Reason saveReason(Reason reason) {
        return reasonRepository.save(reason);
    }

    public void deleteReason(int id) {
        reasonRepository.deleteById(id);
    }
    
    public ReasonDTO getReason(int id) { 
        Reason reason = this.reasonRepository.findById(id).get(); 
        ReasonDTO reasonDto = this.modelMapper.map(reason, ReasonDTO.class); 
        return reasonDto; 
    } 
}