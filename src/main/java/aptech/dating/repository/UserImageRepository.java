package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aptech.dating.model.User;
import aptech.dating.model.UserImage;
import jakarta.transaction.Transactional;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
	@Transactional
	@Query("SELECT ui.imagePath FROM UserImage ui WHERE ui.user.id = :userId")
	List<String> findImagePathsByUserId(int userId);
}
