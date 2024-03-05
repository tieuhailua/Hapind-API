package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Block;


public interface BlockRepository extends JpaRepository<Block, Integer> {
    public List<Block> findByUserByUseId_Id(Integer userId);
}
