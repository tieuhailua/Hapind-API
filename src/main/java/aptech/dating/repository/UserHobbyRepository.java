package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.UserHobby;

public interface UserHobbyRepository extends JpaRepository<UserHobby, Integer> {
	List<UserHobby> findAllByUserId(int userId);
}
