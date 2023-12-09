package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import aptech.dating.model.UserSinger;


public interface UserSingerRepository extends JpaRepository<UserSinger, Integer> {
	List<UserSinger> findAllByUserId(int userId);
}
