package aptech.dating.repository;

import aptech.dating.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Purpose;


public interface PurposeRepository extends JpaRepository<Purpose, Integer> {
    public Purpose findPurposeByUsers(User user);
}
