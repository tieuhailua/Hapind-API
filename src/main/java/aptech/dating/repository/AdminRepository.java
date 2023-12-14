package aptech.dating.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import aptech.dating.model.Admin;



public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Optional<Admin> findByUsername(String username);

	  Boolean existsByUsername(String username);
}
