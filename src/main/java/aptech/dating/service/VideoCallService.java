package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.VideoCallDTO;
import aptech.dating.model.VideoCall;
import aptech.dating.repository.VideoCallRepository;

@Service
public class VideoCallService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final VideoCallRepository videoCallRepository;

    // Use constructor-based dependency injection
    @Autowired
    public VideoCallService(VideoCallRepository videoCallRepository) {
        this.videoCallRepository = videoCallRepository;
    }

    public List<VideoCall> getAllVideoCalls() {
        return videoCallRepository.findAll();
    }

    public Optional<VideoCall> getVideoCallById(int id) {
        return videoCallRepository.findById(id);
    }

    public VideoCall saveVideoCall(VideoCall videoCall) {
        return videoCallRepository.save(videoCall);
    }

    public void deleteVideoCall(int id) {
        videoCallRepository.deleteById(id);
    }
    
    public VideoCallDTO getVideoCall(int id) { 
        VideoCall videoCall = this.videoCallRepository.findById(id).get(); 
        VideoCallDTO videoCallDto = this.modelMapper.map(videoCall, VideoCallDTO.class); 
        return videoCallDto; 
    } 
}