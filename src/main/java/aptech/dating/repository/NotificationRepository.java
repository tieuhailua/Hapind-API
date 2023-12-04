package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Notification;


public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
