package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.UserConversation;


public interface UserConversationRepository extends JpaRepository<UserConversation, Integer> {
}
