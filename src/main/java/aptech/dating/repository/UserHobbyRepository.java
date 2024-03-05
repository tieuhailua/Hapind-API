package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.User;
import aptech.dating.model.UserHobby;

public interface UserHobbyRepository extends JpaRepository<UserHobby, Integer> {
	List<UserHobby> findAllByUserId(int userId);
	List<UserHobby> findAllByUserIdAndChooseIsTrue(int userId);
    UserHobby findUserHobbyByUserAndHobby_Name(User user, String hobbyName);
}
