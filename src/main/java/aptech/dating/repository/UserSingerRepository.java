package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.User;
import aptech.dating.model.UserMusic;
import aptech.dating.model.UserSinger;


public interface UserSingerRepository extends JpaRepository<UserSinger, Integer> {
	List<UserSinger> findAllByUserId(int userId);
    List<UserSinger> findAllByUserIdAndChooseIsTrue(int userId);
    UserSinger findUserSingerByUserAndSinger_Name(User user, String singerName);
}
