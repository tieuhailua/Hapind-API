package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Block;


public interface BlockRepository extends JpaRepository<Block, Integer> {
}
