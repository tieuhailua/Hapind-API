package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.UserHobby;


public interface UserHobbyRepository extends JpaRepository<UserHobby, Integer> {
}
