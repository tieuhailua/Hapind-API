package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.User;
import aptech.dating.model.UserExpecting;



public interface UserExpectingRepository extends JpaRepository<UserExpecting, Integer> {
	List<UserExpecting> findAllByUserId(int userId);
	List<UserExpecting> findAllByUserIdAndChooseIsTrue(int userId);
    UserExpecting findUserExpectingByUserAndExpecting_Name(User user, String ExpectingName);
}
