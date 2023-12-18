package aptech.dating.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.DTO.BannedDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.User;


public interface BannedRepository extends JpaRepository<Banned, Integer> {
	public Optional<Admin> findByAdminId(Integer adminId);
	public Optional<User> findByUserId(Integer userId);
}
