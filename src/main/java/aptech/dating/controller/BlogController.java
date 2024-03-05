package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import aptech.dating.model.Admin;
import aptech.dating.model.Block;
import aptech.dating.model.Blog;
import aptech.dating.model.Family;
import aptech.dating.service.AdminService;
import aptech.dating.service.BlogService;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "http://localhost:4200")
public class BlogController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final BlogService blogService;
	private final AdminService adminService;

	// Use constructor-based dependency injection
	@Autowired
	public BlogController(BlogService blogService, AdminService adminService) {
		this.blogService = blogService;
		this.adminService = adminService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<BlogDTO>> getAllBlogs() {
		List<Blog> blog = blogService.getAllBlogs();

		List<BlogDTO> blogDTO = blog.stream().map(element -> modelMapper.map(element, BlogDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(blogDTO);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<BlogDTO> getBlogById(@PathVariable int id) {
		Optional<Blog> blog = blogService.getBlogById(id);
		BlogDTO blogDTO = modelMapper.map(blog, BlogDTO.class);

		return blogDTO != null ? ResponseEntity.ok(blogDTO) : ResponseEntity.notFound().build();
	}

//	@PostMapping("/{username}")
//	@PreAuthorize("hasAuthority('admin')")
//	public ResponseEntity<Blog> createBlogImage(@RequestBody @Validated BlogDTO blogDTO,
//			@PathVariable String username) {
//		Optional<Blog> blog = blogService.getBlogById(blogDTO.getId());
//
//		if (blog.isPresent()) {
//			Blog updateBlog = blog.get();
//			Admin admin = adminService.getAdminByUsername(username).get();
//			blogDTO.setAdmin(admin);
//			Blog blog1 = modelMapper.map(updateBlog, Blog.class);
//			return ResponseEntity.ok(blogService.saveBlog(blog1));
//		}
//
//		Admin admin = adminService.getAdminByUsername(username).get();
//		blogDTO.setAdmin(admin);
//		Blog blog2 = modelMapper.map(blogDTO, Blog.class);
//		return ResponseEntity.ok(blogService.saveBlog(blog2));
//	}
	
	@PostMapping("/{username}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Blog> createBlogImage(@RequestBody @Validated BlogDTO blogDTO,
	        @PathVariable String username) {
	    int blogId = blogDTO.getId();

	    // Check if the provided ID is valid
	    if (blogId != 0 && blogId > 0) {
	        Optional<Blog> existingBlog = blogService.getBlogById(blogId);
	        if (existingBlog.isPresent()) {
	            Blog updateBlog = existingBlog.get();
	            Admin admin = adminService.getAdminByUsername(username).get();
	            blogDTO.setAdmin(admin);
				Blog blog1 = modelMapper.map(blogDTO, Blog.class);

	            // Update the existing blog and return it
	            return ResponseEntity.ok(blogService.saveBlog(blog1));
	        } else {
	            // Return a Bad Request response if the provided ID is not valid
	            return ResponseEntity.badRequest().build();
	        }
	    }

	    // Create a new blog
	    Admin admin = adminService.getAdminByUsername(username).orElseThrow();
	    blogDTO.setAdmin(admin);
		Blog newBlog2 = modelMapper.map(blogDTO, Blog.class);

	    return ResponseEntity.ok(blogService.saveBlog(newBlog2));
	}

	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
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
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteBlog(@PathVariable int id) {
		blogService.deleteBlog(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<BlogDTO> getBlog(@PathVariable("id") int id) {
		blogService.deleteBlog(id);
		return ResponseEntity.ok().build();
	}
}
