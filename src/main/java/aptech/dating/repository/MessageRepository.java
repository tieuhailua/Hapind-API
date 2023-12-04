package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Message;


public interface MessageRepository extends JpaRepository<Message, Integer> {
}
