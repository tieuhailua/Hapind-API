package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.MusicDTO;
import aptech.dating.model.Music;
import aptech.dating.repository.MusicRepository;

@Service
public class MusicService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final MusicRepository musicRepository;

    // Use constructor-based dependency injection
    @Autowired
    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<Music> getAllMusics() {
        return musicRepository.findAll();
    }

    public Optional<Music> getMusicById(int id) {
        return musicRepository.findById(id);
    }

    public Music saveMusic(Music music) {
        return musicRepository.save(music);
    }

    public void deleteMusic(int id) {
    	musicRepository.deleteById(id);
    }
    
    public MusicDTO getMusic(int id) { 
        Music music = this.musicRepository.findById(id).get(); 
        MusicDTO musicDto = this.modelMapper.map(music, MusicDTO.class); 
        return musicDto; 
    } 
}


