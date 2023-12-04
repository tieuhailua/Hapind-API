package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aptech.dating.DTO.FamilyDTO;
import aptech.dating.DTO.NotificationDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Notification;
import aptech.dating.service.NotificationService;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final NotificationService notificationService;

	// Use constructor-based dependency injection
	@Autowired
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@GetMapping
	public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
		List<Notification> notification = notificationService.getAllNotifications();

		List<NotificationDTO> notificationDTO = notification.stream().map(element -> modelMapper.map(element, NotificationDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(notificationDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable int id) {
		Optional<Notification> notification = notificationService.getNotificationById(id);

		NotificationDTO notificationDTO = modelMapper.map(notification, NotificationDTO.class);
		
		return notificationDTO!=null?ResponseEntity.ok(notificationDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Notification> createNotification(@RequestBody @Validated NotificationDTO notificationDTO) {
		Notification notification = modelMapper.map(notificationDTO, Notification.class);
		return ResponseEntity.ok(notificationService.saveNotification(notification));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Notification> updateNotification(@PathVariable int id, @RequestBody @Validated NotificationDTO notificationDTO) {
		Optional<Notification> notification = notificationService.getNotificationById(id);

	    if (notification.isPresent()) {
	    	Notification updateNotification = notification.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(notificationDTO, updateNotification);

	        // Save the updated admin
	        return ResponseEntity.ok(notificationService.saveNotification(updateNotification));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNotification(@PathVariable int id) {
		notificationService.deleteNotification(id);
		return ResponseEntity.ok().build();
	}
}
