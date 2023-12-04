package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Expecting;


public interface ExpectingRepository extends JpaRepository<Expecting, Integer> {
}
