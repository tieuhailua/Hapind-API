package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.NotificationDTO;
import aptech.dating.model.Notification;
import aptech.dating.repository.NotificationRepository;

@Service
public class NotificationService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final NotificationRepository notificationRepository;

    // Use constructor-based dependency injection
    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(int id) {
        return notificationRepository.findById(id);
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(int id) {
    	notificationRepository.deleteById(id);
    }
    
    public NotificationDTO getNotification(int id) { 
        Notification notification = this.notificationRepository.findById(id).get(); 
        NotificationDTO notificationDto = this.modelMapper.map(notification, NotificationDTO.class); 
        return notificationDto; 
    } 
}



