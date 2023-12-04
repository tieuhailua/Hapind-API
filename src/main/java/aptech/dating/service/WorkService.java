package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.WorkDTO;
import aptech.dating.model.Work;
import aptech.dating.repository.WorkRepository;

@Service
public class WorkService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final WorkRepository workRepository;

    // Use constructor-based dependency injection
    @Autowired
    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public List<Work> getAllWorks() {
        return workRepository.findAll();
    }

    public Optional<Work> getWorkById(int id) {
        return workRepository.findById(id);
    }

    public Work saveWork(Work work) {
        return workRepository.save(work);
    }

    public void deleteWork(int id) {
        workRepository.deleteById(id);
    }
    
    public WorkDTO getWork(int id) { 
        Work work = this.workRepository.findById(id).get(); 
        WorkDTO workDto = this.modelMapper.map(work, WorkDTO.class); 
        return workDto; 
    } 
}