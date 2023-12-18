package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import aptech.dating.DTO.BlogImageDTO;
import aptech.dating.DTO.FamilyDTO;
import aptech.dating.model.Blog;
import aptech.dating.model.BlogImage;
import aptech.dating.model.Family;
import aptech.dating.service.BlogImageService;

@RestController
@RequestMapping("/api/blogImage")
@CrossOrigin(origins = "http://localhost:4200")
public class BlogImageController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final BlogImageService blogImageService;

	// Use constructor-based dependency injection
	@Autowired
	public BlogImageController(BlogImageService blogImageService) {
		this.blogImageService = blogImageService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<BlogImageDTO>> getAllBlogImages() {
		List<BlogImage> blogImage = blogImageService.getAllBlogImages();

		List<BlogImageDTO> blogImageDTO = blogImage.stream().map(element -> modelMapper.map(element, BlogImageDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(blogImageDTO);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<BlogImageDTO> getBlogImageById(@PathVariable int id) {
		Optional<BlogImage> blogImage = blogImageService.getBlogImageById(id);

		BlogImageDTO blogImageDTO = modelMapper.map(blogImage, BlogImageDTO.class);
		
		return blogImageDTO!=null?ResponseEntity.ok(blogImageDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<BlogImage> createBlogImage(@RequestBody @Validated BlogImageDTO blogImageDTO) {
		BlogImage blogImage = modelMapper.map(blogImageDTO, BlogImage.class);
		return ResponseEntity.ok(blogImageService.saveBlogImage(blogImage));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<BlogImage> updateBlogImage(@PathVariable int id, @Validated BlogImageDTO blogImageDTO) {
		Optional<BlogImage> blogImage = blogImageService.getBlogImageById(id);

	    if (blogImage.isPresent()) {
	    	BlogImage updateBlogImage = blogImage.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(blogImageDTO, updateBlogImage);

	        // Save the updated admin
	        return ResponseEntity.ok(blogImageService.saveBlogImage(updateBlogImage));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteBlogImage(@PathVariable int id) {
		blogImageService.deleteBlogImage(id);
		return ResponseEntity.ok().build();
	}
}

