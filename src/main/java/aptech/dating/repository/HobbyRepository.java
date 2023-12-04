package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Hobby;


public interface HobbyRepository extends JpaRepository<Hobby, Integer> {
}
