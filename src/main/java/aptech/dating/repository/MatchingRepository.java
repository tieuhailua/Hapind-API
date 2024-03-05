package aptech.dating.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import aptech.dating.model.Matching;
import aptech.dating.model.User;

public interface MatchingRepository extends JpaRepository<Matching, Integer> {

	@Query("SELECT m.userByFirstUserId FROM Matching m "
			+ "WHERE m.statusId = -3 AND m.userBySecondUserId.id = :loginId")
	public List<User> findMatchingUser(@Param("loginId") Integer loginId);

//	@Query("SELECT m.userByFirstUserId FROM Matching m "
//	        + "WHERE m.statusId = -1 AND (m.userBySecondUserId.id = :loginId OR m.userByFirstUserId.id = :loginId)")
//	public List<User> findChatUser(@Param("loginId") Integer loginId);

	@Query("SELECT m.userByFirstUserId FROM Matching m "
			+ "WHERE m.statusId = -1 AND m.userBySecondUserId.id = :loginId " + "UNION "
			+ "SELECT m.userBySecondUserId FROM Matching m "
			+ "WHERE m.statusId = -1 AND m.userByFirstUserId.id = :loginId")
	public List<User> findChatUser(@Param("loginId") Integer loginId);

	@Query("SELECT m FROM Matching m WHERE m.userByFirstUserId.id = :firstUserId AND m.userBySecondUserId.id = :secondUserId")
	Optional<Matching> findByUserByFirstUserIdAndUserBySecondUserId(@Param("firstUserId") Integer firstUserId,
			@Param("secondUserId") Integer secondUserId);

	@Query("SELECT m FROM Matching m WHERE (m.userByFirstUserId.id = :firstUserId AND m.userBySecondUserId.id = :secondUserId) OR (m.userByFirstUserId.id = :secondUserId AND m.userBySecondUserId.id = :firstUserId)")
	Optional<Matching> findByUserIds(@Param("firstUserId") Integer firstUserId,
			@Param("secondUserId") Integer secondUserId);

}
