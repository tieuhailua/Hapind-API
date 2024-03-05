package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.SingerDTO;
import aptech.dating.model.Expecting;
import aptech.dating.model.Singer;
import aptech.dating.repository.SingerRepository;

@Service
public class SingerService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final SingerRepository singerRepository;

    // Use constructor-based dependency injection
    @Autowired
    public SingerService(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    public List<Singer> getAllSingers() {
        return singerRepository.findAll();
    }

    public Optional<Singer> getSingerById(int id) {
        return singerRepository.findById(id);
    }
    
    public Singer getSingerByName(String name) {
		return singerRepository.findSingerByName(name);
	}
    
    public Singer saveSinger(Singer singer) {
        return singerRepository.save(singer);
    }

    public void deleteSinger(int id) {
        singerRepository.deleteById(id);
    }
    
    public SingerDTO getSinger(int id) { 
        Singer singer = this.singerRepository.findById(id).get(); 
        SingerDTO singerDto = this.modelMapper.map(singer, SingerDTO.class); 
        return singerDto; 
    } 
}