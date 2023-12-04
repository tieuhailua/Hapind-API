package aptech.dating.DTO;

import aptech.dating.model.Blog;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BlogImageDTO {
	private Integer id;
	@NotNull(message="Blog must be selected")
	private Blog blog;
	
	@NotEmpty(message="Image Path can't be blank")
	private String imagePath;

	public BlogImageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlogImageDTO(@NotNull(message = "Blog must be selected") Blog blog,
			@NotEmpty(message = "Image Path can't be blank") String imagePath) {
		super();
		this.blog = blog;
		this.imagePath = imagePath;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
