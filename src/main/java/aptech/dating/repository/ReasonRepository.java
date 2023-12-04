package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Reason;


public interface ReasonRepository extends JpaRepository<Reason, Integer> {
}
