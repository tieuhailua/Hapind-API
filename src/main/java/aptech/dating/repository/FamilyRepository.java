package aptech.dating.repository;

import aptech.dating.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Family;


public interface FamilyRepository extends JpaRepository<Family, Integer> {
    public Family findFamilyByUsers(User user);
}
