package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Hobby;
import aptech.dating.model.Reason;


public interface ReasonRepository extends JpaRepository<Reason, Integer> {
	Reason findReasonByName(String name);
}
