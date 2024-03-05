package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.User;
import aptech.dating.model.UserExercise;



public interface UserExerciseRepository extends JpaRepository<UserExercise, Integer> {
	List<UserExercise> findAllByUserId(int userId);
	List<UserExercise> findAllByUserIdAndChooseIsTrue(int userId);
    UserExercise findUserExerciseByUserAndExercise_Name(User user, String ExerciseName);
}
