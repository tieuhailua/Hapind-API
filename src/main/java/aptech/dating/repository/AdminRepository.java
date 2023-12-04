package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.DTO.AdminDTO;
import aptech.dating.model.Admin;



public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
