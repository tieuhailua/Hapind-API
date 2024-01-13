package aptech.dating.repository;

import aptech.dating.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Smoking;


public interface SmokingRepository extends JpaRepository<Smoking, Integer> {
    public Smoking findSmokingsByUsers(User user);
}
