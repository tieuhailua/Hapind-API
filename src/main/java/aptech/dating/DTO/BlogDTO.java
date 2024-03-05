package aptech.dating.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.Admin;
import aptech.dating.model.BlogImage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BlogDTO {
	private Integer id;
	//@NotNull(message = "Admin must be selected")
	private Admin admin;
	
	@NotEmpty(message = "Title can't be blank")
	private String title;
	
	@NotEmpty(message = "Brief can't be blank")
	private String brief;
	
	@NotEmpty(message = "Content can't be blank")
	private String content;
	
	@NotNull(message = "Publish date must be selected")
	private Date publishDate;
	
	@NotEmpty(message = "Status can't be blank")
	private String status;
	
	//@NotNull(message = "Blog Image date must be selected")
	private Set<BlogImage> blogImages = new HashSet<BlogImage>(0);

	public BlogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlogDTO(Admin admin,
			@NotEmpty(message = "Title can't be blank") String title,
			@NotEmpty(message = "Brief can't be blank") String brief,
			@NotEmpty(message = "Content can't be blank") String content,
			@NotNull(message = "Publish date must be selected") Date publishDate,
			@NotEmpty(message = "Status can't be blank") String status,
			Set<BlogImage> blogImages) {
		super();
		this.admin = admin;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.publishDate = publishDate;
		this.status = status;
		this.blogImages = blogImages;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<BlogImage> getBlogImages() {
		return blogImages;
	}

	public void setBlogImages(Set<BlogImage> blogImages) {
		this.blogImages = blogImages;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
