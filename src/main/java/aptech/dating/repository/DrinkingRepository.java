package aptech.dating.repository;

import aptech.dating.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Drinking;
import aptech.dating.model.Purpose;

import java.util.Optional;


public interface DrinkingRepository extends JpaRepository<Drinking, Integer> {
    public Drinking findDrinkingByUsers(User user);
    public Drinking findDrinkingByName(String name);

}
