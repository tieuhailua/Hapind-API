package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.DTO.BannedDTO;
import aptech.dating.model.Banned;


public interface BannedRepository extends JpaRepository<Banned, Integer> {

}
