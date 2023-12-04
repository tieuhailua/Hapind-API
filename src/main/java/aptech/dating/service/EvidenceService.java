package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.EvidenceDTO;
import aptech.dating.model.Evidence;
import aptech.dating.repository.EvidenceRepository;

@Service
public class EvidenceService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final EvidenceRepository evidenceRepository;

    // Use constructor-based dependency injection
    @Autowired
    public EvidenceService(EvidenceRepository evidenceRepository) {
        this.evidenceRepository = evidenceRepository;
    }

    public List<Evidence> getAllEvidences() {
        return evidenceRepository.findAll();
    }

    public Optional<Evidence> getEvidenceById(int id) {
        return evidenceRepository.findById(id);
    }

    public Evidence saveEvidence(Evidence evidence) {
        return evidenceRepository.save(evidence);
    }

    public void deleteEvidence(int id) {
    	evidenceRepository.deleteById(id);
    }
    
    public EvidenceDTO getEvidence(int id) { 
        Evidence evidence = this.evidenceRepository.findById(id).get(); 
        EvidenceDTO evidenceDto = this.modelMapper.map(evidence, EvidenceDTO.class); 
        return evidenceDto; 
    } 
}



