package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.BlogDTO;
import aptech.dating.model.Blog;
import aptech.dating.repository.BlogRepository;

@Service
public class BlogService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final BlogRepository blogRepository;

    // Use constructor-based dependency injection
    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
    
    public List<Blog> getAllBlogsPublish() {
        return blogRepository.findByStatusPublished();
    }

    public Optional<Blog> getBlogById(int id) {
        return blogRepository.findById(id);
    }

    public Blog saveBlog(Blog Blog) {
        return blogRepository.save(Blog);
    }

    public void deleteBlog(int id) {
    	blogRepository.deleteById(id);
    }
    
    public BlogDTO getBlog(int id) { 
        Blog blog = this.blogRepository.findById(id).get(); 
        BlogDTO blogDto = this.modelMapper.map(blog, BlogDTO.class); 
        return blogDto; 
    } 
}
