package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Exercise;


public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
	Exercise findExerciseByName(String name);
}
