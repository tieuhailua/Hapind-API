package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aptech.dating.DTO.AdminDTO;
import aptech.dating.DTO.BlogDTO;
import aptech.dating.model.Blog;


public interface BlogRepository extends JpaRepository<Blog, Integer> {
	@Query("SELECT b FROM Blog b WHERE b.status = 'Published'")
    public List<Blog> findByStatusPublished();
}
