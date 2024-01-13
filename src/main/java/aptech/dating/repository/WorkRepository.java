package aptech.dating.repository;

import aptech.dating.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Work;


public interface WorkRepository extends JpaRepository<Work, Integer> {
    public Work findWorkByUsers(User user);
}
