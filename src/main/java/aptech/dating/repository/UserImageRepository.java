package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import aptech.dating.model.User;
import aptech.dating.model.UserImage;
import jakarta.transaction.Transactional;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
//	@Transactional
//	@Query("SELECT ui.imagePath FROM UserImage ui WHERE ui.user.id = :userId")
//	List<String> findImagePathsByUserId(int userId);

//	@Query(value = "SELECT u FROM User u WHERE u.status.name = :statusName ORDER BY RAND() LIMIT 1")
//	public User findRandomUserByStatusName(@Param("statusName") String statusName);

	@Query("SELECT ui FROM UserImage ui WHERE ui.user.id = :userId ORDER BY ui.id ASC")
	List<UserImage> findImagePathsByUserId(@Param("userId") int userId);

	UserImage findByUserAndId(User user, Integer id);

	long countByUser(User user);

	@Modifying
	@Query("UPDATE UserImage SET imagePath = :imagePath WHERE user = :user AND id = :id")
	void updateImagePath(@Param("user") User user, @Param("id") Integer id, @Param("imagePath") String imagePath);
}
