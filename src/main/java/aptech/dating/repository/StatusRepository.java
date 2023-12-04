package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Status;


public interface StatusRepository extends JpaRepository<Status, Integer> {
}
