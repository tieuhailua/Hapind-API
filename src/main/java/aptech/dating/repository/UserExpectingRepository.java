package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.UserExpecting;


public interface UserExpectingRepository extends JpaRepository<UserExpecting, Integer> {
}
