package aptech.dating.repository;

import aptech.dating.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Habit;


public interface HabitRepository extends JpaRepository<Habit, Integer> {
    public Habit findHabitByUsers(User user);
    public Habit findHabitByName(String name);
}
