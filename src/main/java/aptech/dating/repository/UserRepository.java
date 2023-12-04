package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
}
