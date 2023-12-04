package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.DTO.AdminDTO;
import aptech.dating.DTO.BlogDTO;
import aptech.dating.model.Blog;


public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
}
