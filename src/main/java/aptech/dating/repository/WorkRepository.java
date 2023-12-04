package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Work;


public interface WorkRepository extends JpaRepository<Work, Integer> {
}
