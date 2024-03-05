package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Conversation;


public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
}
