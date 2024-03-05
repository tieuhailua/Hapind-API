package aptech.dating.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aptech.dating.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Optional<Admin> findByUsername(String username);

	Boolean existsByUsername(String username);

	@Query("SELECT a FROM Admin a WHERE a.role = 'mod'")
	public List<Admin> findByRoleMod();
}
