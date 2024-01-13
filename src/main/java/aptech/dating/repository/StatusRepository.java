package aptech.dating.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Status;


public interface StatusRepository extends JpaRepository<Status, Integer> {
	public Optional<Status> findByName(String name);
}
