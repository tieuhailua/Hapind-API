package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Evidence;


public interface EvidenceRepository extends JpaRepository<Evidence, Integer> {
}
