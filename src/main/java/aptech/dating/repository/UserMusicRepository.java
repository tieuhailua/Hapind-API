package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.User;
import aptech.dating.model.UserMusic;


public interface UserMusicRepository extends JpaRepository<UserMusic, Integer> {
	List<UserMusic> findAllByUserId(int userId);
    List<UserMusic> findAllByUserIdAndChooseIsTrue(int userId);
    UserMusic findUserMusicByUserAndMusic_Name(User user, String musicName);
}
