package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Matching;


public interface MatchingRepository extends JpaRepository<Matching, Integer> {
}
