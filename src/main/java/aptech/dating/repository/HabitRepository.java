package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Habit;


public interface HabitRepository extends JpaRepository<Habit, Integer> {
}
