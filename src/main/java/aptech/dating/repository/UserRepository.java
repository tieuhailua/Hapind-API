package aptech.dating.repository;

import aptech.dating.model.*;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Modifying
	@Query("Update User u set u.status.id = :status where u.id = :id")
	public void banUser(@Param("status") Integer status, @Param("id") Integer id);

	public Optional<User> findByEmail(String email);

	public Optional<User> findByPhone(String phone);

	@Transactional
	@Modifying
	@Query("INSERT INTO User (email, password) VALUES (:email, :password)")
	public void signUp(@Param("email") String email, @Param("password") String password);

//    @Transactional
//    @Query(value = "SELECT * FROM \"user\" ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
//    public User findRandomUser();
	@Transactional
	@Query(value = "SELECT * FROM \"user\" "
			+ "WHERE id NOT IN (SELECT blocked_id FROM block WHERE user_id = :loginUserId) "
			+ "AND id NOT IN (SELECT user_id FROM block WHERE blocked_id = :loginUserId) "
			+ "AND id NOT IN (SELECT reported_id FROM report WHERE reporter_id = :loginUserId) "
			+ "  AND id NOT IN (SELECT user_id FROM banned) " + "  AND id <> :loginUserId "
			+ "  AND id NOT IN (SELECT second_user_id FROM matching WHERE first_user_id = :loginUserId) "
			+ "  AND id NOT IN (SELECT first_user_id FROM matching WHERE second_user_id = :loginUserId) "
			+ "  AND (gender = (SELECT finding FROM \"user\" WHERE id = :loginUserId) OR (SELECT finding FROM \"user\" WHERE id = :loginUserId) = 'Everyone')"
			+ "  AND EXTRACT(YEAR FROM AGE(dob)) BETWEEN :minAge AND :maxAge " + "  AND distance < :maxDistance "
			+ "ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	public User findRandomUser(@Param("loginUserId") Integer loginUserId, @Param("minAge") int minAge,
			@Param("maxAge") int maxAge, @Param("maxDistance") int maxDistance);

	@Query(value = "SELECT u FROM User u WHERE u.status.name = :statusName ORDER BY RANDOM() LIMIT 1")
	public User findRandomUserByStatusName(@Param("statusName") String statusName);

//	@Transactional
//	@Query(value = "SELECT * FROM \"user\" "
//			+ "WHERE id NOT IN (SELECT blocked_id FROM block WHERE user_id = :loginUserId) "
//			+ "AND id NOT IN (SELECT user_id FROM block WHERE blocked_id = :loginUserId) "
//			+ "  AND id NOT IN (SELECT user_id FROM banned) "
//			+ "  AND id <> :loginUserId "
//			+ "  AND id NOT IN (SELECT second_user_id FROM matching WHERE first_user_id = :loginUserId) "
//	        + "  AND (gender = (SELECT finding FROM \"user\" WHERE id = :loginUserId) OR (SELECT finding FROM \"user\" WHERE id = :loginUserId) = 'Everyone')"
//			+ "  AND EXTRACT(YEAR FROM AGE(dob)) BETWEEN :minAge AND :maxAge "
//			+ "  AND distance < :maxDistance "
//	        + "	 AND id IN (SELECT u.id FROM User u WHERE u.purpose.name = :purposeName) "
//			+ "ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
//	public User findRandomUserByPurposeName(@Param("loginUserId") Integer loginUserId, @Param("minAge") int minAge,
//			@Param("maxAge") int maxAge, @Param("maxDistance") int maxDistance,@Param("purposeName") String purposeName);

	@Transactional
	@Query(value = "SELECT * FROM \"user\" "
			+ "WHERE id NOT IN (SELECT blocked_id FROM block WHERE user_id = :loginUserId) "
			+ "AND id NOT IN (SELECT user_id FROM block WHERE blocked_id = :loginUserId) "
			+ "AND id NOT IN (SELECT user_id FROM banned) "
			+ "AND id NOT IN (SELECT reported_id FROM report WHERE reporter_id = :loginUserId) "
			+ "AND id <> :loginUserId "
			+ "AND id NOT IN (SELECT second_user_id FROM matching WHERE first_user_id = :loginUserId) "
			+ "AND id NOT IN (SELECT first_user_id FROM matching WHERE second_user_id = :loginUserId) "
			+ "AND (gender = (SELECT finding FROM \"user\" WHERE id = :loginUserId) OR (SELECT finding FROM \"user\" WHERE id = :loginUserId) = 'Everyone') "
			+ "AND EXTRACT(YEAR FROM AGE(dob)) BETWEEN :minAge AND :maxAge " + "AND distance < :maxDistance "
			+ "AND id IN (SELECT u.id FROM \"user\" u INNER JOIN purpose p ON u.purpose_id = p.id WHERE p.name = :purposeName) "
			+ "ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	public User findRandomUserByPurposeName(@Param("loginUserId") Integer loginUserId, @Param("minAge") int minAge,
			@Param("maxAge") int maxAge, @Param("maxDistance") int maxDistance,
			@Param("purposeName") String purposeName);

	@Transactional
	@Query(value = "SELECT * FROM \"user\" "
			+ "WHERE id NOT IN (SELECT blocked_id FROM block WHERE user_id = :loginUserId) "
			+ "AND id NOT IN (SELECT user_id FROM block WHERE blocked_id = :loginUserId) "
			+ "  AND id NOT IN (SELECT user_id FROM banned) "
			+ "AND id NOT IN (SELECT reported_id FROM report WHERE reporter_id = :loginUserId) "
			+ "  AND id <> :loginUserId "
			+ "  AND id NOT IN (SELECT second_user_id FROM matching WHERE first_user_id = :loginUserId) "
			+ "  AND id NOT IN (SELECT first_user_id FROM matching WHERE second_user_id = :loginUserId) "
			+ "  AND (gender = (SELECT finding FROM \"user\" WHERE id = :loginUserId) OR (SELECT finding FROM \"user\" WHERE id = :loginUserId) = 'Everyone')"
			+ "  AND EXTRACT(YEAR FROM AGE(dob)) BETWEEN :minAge AND :maxAge " + "  AND distance < :maxDistance "
			+ "  AND online = :online " + "ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	public User findRandomUserByOnline(@Param("loginUserId") Integer loginUserId, @Param("minAge") int minAge,
			@Param("maxAge") int maxAge, @Param("maxDistance") int maxDistance, @Param("online") Boolean online);

//	@Transactional
//	@Query(value = "SELECT * FROM \"user\" "
//			+ "WHERE id NOT IN (SELECT blocked_id FROM block WHERE user_id = :loginUserId) "
//			+ "AND id NOT IN (SELECT user_id FROM block WHERE blocked_id = :loginUserId) "
//			+ "  AND id NOT IN (SELECT user_id FROM banned) "
//			+ "  AND id <> :loginUserId "
//			+ "  AND id NOT IN (SELECT second_user_id FROM matching WHERE first_user_id = :loginUserId) "
//	        + "  AND (gender = (SELECT finding FROM \"user\" WHERE id = :loginUserId) OR (SELECT finding FROM \"user\" WHERE id = :loginUserId) = 'Everyone')"
//			+ "  AND EXTRACT(YEAR FROM AGE(dob)) BETWEEN :minAge AND :maxAge "
//			+ "  AND distance < :maxDistance "
//			+ "  AND u.userPets IS NOT EMPTY "
//			+ "ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
//	public User findRandomUserWithUserPets(@Param("loginUserId") Integer loginUserId, @Param("minAge") int minAge,
//			@Param("maxAge") int maxAge, @Param("maxDistance") int maxDistance);
	// Assuming the actual column name is user_pets
	@Query(value = "SELECT * FROM \"user\" u "
			+ "WHERE u.id NOT IN (SELECT blocked_id FROM block WHERE user_id = :loginUserId) "
			+ "AND u.id NOT IN (SELECT user_id FROM block WHERE blocked_id = :loginUserId) "
			+ "AND u.id NOT IN (SELECT user_id FROM banned) "
			+ "AND u.id NOT IN (SELECT reported_id FROM report WHERE reporter_id = :loginUserId) "
			+ "AND u.id <> :loginUserId "
			+ "AND u.id NOT IN (SELECT second_user_id FROM matching WHERE first_user_id = :loginUserId) "
			+ "AND u.id NOT IN (SELECT first_user_id FROM matching WHERE second_user_id = :loginUserId) "
			+ "AND (u.gender = (SELECT finding FROM \"user\" WHERE id = :loginUserId) OR (SELECT finding FROM \"user\" WHERE id = :loginUserId) = 'Everyone')"
			+ "AND EXTRACT(YEAR FROM AGE(u.dob)) BETWEEN :minAge AND :maxAge " + "AND u.distance < :maxDistance "
			+ "AND EXISTS (SELECT 1 FROM user_pet up WHERE up.user_id = u.id) "
			+ "ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	public User findRandomUserWithUserPets(@Param("loginUserId") Integer loginUserId, @Param("minAge") int minAge,
			@Param("maxAge") int maxAge, @Param("maxDistance") int maxDistance);

	@Query(value = "SELECT * FROM \"user\" u "
			+ "WHERE u.id NOT IN (SELECT blocked_id FROM block WHERE user_id = :loginUserId) "
			+ "AND u.id NOT IN (SELECT user_id FROM block WHERE blocked_id = :loginUserId) "
			+ "AND u.id NOT IN (SELECT user_id FROM banned) " + "AND u.id <> :loginUserId "
			+ "AND u.id NOT IN (SELECT reported_id FROM report WHERE reporter_id = :loginUserId) "
			+ "AND u.id NOT IN (SELECT second_user_id FROM matching WHERE first_user_id = :loginUserId) "
			+ "AND u.id NOT IN (SELECT first_user_id FROM matching WHERE second_user_id = :loginUserId) "
			+ "AND (u.gender = (SELECT finding FROM \"user\" WHERE id = :loginUserId) OR (SELECT finding FROM \"user\" WHERE id = :loginUserId) = 'Everyone')"
			+ "AND EXTRACT(YEAR FROM AGE(u.dob)) BETWEEN :minAge AND :maxAge " + "AND u.distance < :maxDistance "
			+ "AND EXISTS (SELECT 1 FROM user_exercise ue WHERE ue.user_id = u.id) "
			+ "ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	public User findRandomUserWithUserExercises(@Param("loginUserId") Integer loginUserId, @Param("minAge") int minAge,
			@Param("maxAge") int maxAge, @Param("maxDistance") int maxDistance);

	@Query(value = "SELECT * FROM \"user\" u "
			+ "WHERE u.id NOT IN (SELECT blocked_id FROM block WHERE user_id = :loginUserId) "
			+ "AND u.id NOT IN (SELECT user_id FROM block WHERE blocked_id = :loginUserId) "
			+ "AND u.id NOT IN (SELECT user_id FROM banned) "
			+ "AND u.id NOT IN (SELECT reported_id FROM report WHERE reporter_id = :loginUserId) "
			+ "AND u.id <> :loginUserId "
			+ "AND u.id NOT IN (SELECT second_user_id FROM matching WHERE first_user_id = :loginUserId) "
			+ "AND u.id NOT IN (SELECT first_user_id FROM matching WHERE second_user_id = :loginUserId) "
			+ "AND (u.gender = (SELECT finding FROM \"user\" WHERE id = :loginUserId) OR (SELECT finding FROM \"user\" WHERE id = :loginUserId) = 'Everyone')"
			+ "AND EXTRACT(YEAR FROM AGE(u.dob)) BETWEEN :minAge AND :maxAge " + "AND u.distance < :maxDistance "
			+ "AND EXISTS (SELECT 1 FROM user_music um WHERE um.user_id = u.id) "
			+ "ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	public User findRandomUserWithUserMusics(@Param("loginUserId") Integer loginUserId, @Param("minAge") int minAge,
			@Param("maxAge") int maxAge, @Param("maxDistance") int maxDistance);

//	@Query(value = "SELECT u FROM User u WHERE u.purpose.name = :purposeName ORDER BY RANDOM() LIMIT 1")
//	public User findRandomUserByPurposeName(@Param("purposeName") String purposeName);
//
//	@Query(value = "SELECT u FROM User u WHERE u.online = :online ORDER BY RANDOM() LIMIT 1")
//	public User findRandomUserByOnline(@Param("online") Boolean online);
//
//	@Query(value = "SELECT u FROM User u WHERE u.userPets IS NOT EMPTY ORDER BY RANDOM() LIMIT 1")
//	public User findRandomUserWithUserPets();
}
