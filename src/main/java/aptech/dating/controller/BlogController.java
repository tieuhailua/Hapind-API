package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aptech.dating.DTO.AdminDTO;
import aptech.dating.DTO.BlogDTO;
import aptech.dating.DTO.FamilyDTO;
import aptech.dating.model.Block;
import aptech.dating.model.Blog;
import aptech.dating.model.Family;
import aptech.dating.service.BlogService;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class BlogController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final BlogService blogService;

	// Use constructor-based dependency injection
	@Autowired
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@GetMapping
	public ResponseEntity<List<BlogDTO>> getAllBlogs() {
		List<Blog> blog = blogService.getAllBlogs();

		List<BlogDTO> blogDTO = blog.stream().map(element -> modelMapper.map(element, BlogDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(blogDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BlogDTO> getBlogById(@PathVariable int id) {
		Optional<Blog> blog = blogService.getBlogById(id);

		BlogDTO blogDTO = modelMapper.map(blog, BlogDTO.class);
		
		return blogDTO!=null?ResponseEntity.ok(blogDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Blog> createBlogImage(@RequestBody @Validated BlogDTO blogDTO) {
		Blog blog = modelMapper.map(blogDTO, Blog.class);
		return ResponseEntity.ok(blogService.saveBlog(blog));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable int id, @RequestBody @Validated BlogDTO blogDTO) {
		Optional<Blog> blog = blogService.getBlogById(id);

	    if (blog.isPresent()) {
	    	Blog updateBlog = blog.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(blogDTO, updateBlog);

	        // Save the updated admin
	        return ResponseEntity.ok(blogService.saveBlog(updateBlog));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBlog(@PathVariable int id) {
		blogService.deleteBlog(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/get/{id}") 
    public ResponseEntity<BlogDTO> getBlog(@PathVariable("id") int id){ 
        blogService.deleteBlog(id);
        return ResponseEntity.ok().build();
    } 
}


