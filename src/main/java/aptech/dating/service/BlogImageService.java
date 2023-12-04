package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.BlogImageDTO;
import aptech.dating.model.BlogImage;
import aptech.dating.repository.BlogImageRepository;

@Service
public class BlogImageService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final BlogImageRepository blogImageRepository;

    // Use constructor-based dependency injection
    @Autowired
    public BlogImageService(BlogImageRepository blogImageRepository) {
        this.blogImageRepository = blogImageRepository;
    }

    public List<BlogImage> getAllBlogImages() {
        return blogImageRepository.findAll();
    }

    public Optional<BlogImage> getBlogImageById(int id) {
        return blogImageRepository.findById(id);
    }

    public BlogImage saveBlogImage(BlogImage blogImage) {
        return blogImageRepository.save(blogImage);
    }

    public void deleteBlogImage(int id) {
    	blogImageRepository.deleteById(id);
    }
    
    public BlogImageDTO getBlockImage(int id) { 
        BlogImage blogImage = this.blogImageRepository.findById(id).get(); 
        BlogImageDTO blogImageDto = this.modelMapper.map(blogImage, BlogImageDTO.class); 
        return blogImageDto; 
    } 
}


