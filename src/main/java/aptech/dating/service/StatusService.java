package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.StatusDTO;
import aptech.dating.model.Status;
import aptech.dating.repository.StatusRepository;

@Service
public class StatusService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final StatusRepository statusRepository;

    // Use constructor-based dependency injection
    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatuss() {
        return statusRepository.findAll();
    }

    public Optional<Status> getStatusById(int id) {
        return statusRepository.findById(id);
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    public void deleteStatus(int id) {
        statusRepository.deleteById(id);
    }
    
    public StatusDTO getStatus(int id) { 
        Status status = this.statusRepository.findById(id).get(); 
        StatusDTO statusDto = this.modelMapper.map(status, StatusDTO.class); 
        return statusDto; 
    } 
}