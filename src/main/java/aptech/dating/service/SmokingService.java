package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.SmokingDTO;
import aptech.dating.model.Smoking;
import aptech.dating.repository.SmokingRepository;

@Service
public class SmokingService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final SmokingRepository smokingRepository;

    // Use constructor-based dependency injection
    @Autowired
    public SmokingService(SmokingRepository smokingRepository) {
        this.smokingRepository = smokingRepository;
    }

    public List<Smoking> getAllSmokings() {
        return smokingRepository.findAll();
    }

    public Optional<Smoking> getSmokingById(int id) {
        return smokingRepository.findById(id);
    }

    public Smoking saveSmoking(Smoking smoking) {
        return smokingRepository.save(smoking);
    }

    public void deleteSmoking(int id) {
        smokingRepository.deleteById(id);
    }
    
    public SmokingDTO getSmoke(int id) { 
        Smoking smoking = this.smokingRepository.findById(id).get(); 
        SmokingDTO smokingDto = this.modelMapper.map(smoking, SmokingDTO.class); 
        return smokingDto; 
    } 
}