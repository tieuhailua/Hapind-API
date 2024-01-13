package aptech.dating.DTO;

import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.Banned;
import aptech.dating.model.Blog;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.List;
import jakarta.validation.constraints.Size;

public class AdminDTO {
    private Integer id;

    @NotEmpty(message = "Username can't be blank")
    @Size(min = 6, max = 50, message = "Username must be between 6 and 50 characters")
    private String username;

    @NotEmpty(message = "Password can't be blank")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 32 characters")
    private String password;

    private Set<Banned> banneds = new HashSet<Banned>(0);

    private Set<Blog> blogs = new HashSet<Blog>(0);
    private String role;
    public AdminDTO(
	    @NotEmpty(message = "Username can't be blank") @Size(min = 6, max = 50, message = "Username must be between 6 and 50 characters") String username,
	    @NotEmpty(message = "Password can't be blank") @Size(min = 8, max = 255, message = "Password must be between 8 and 32 characters") String password,
	    Set<Banned> banneds, Set<Blog> blogs, String role) {
	super();
	this.username = username;
	this.password = password;
	this.banneds = banneds;
	this.blogs = blogs;
	this.role = role;
    }

    public AdminDTO() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Set<Banned> getBanneds() {
	return banneds;
    }

    public void setBanneds(Set<Banned> banneds) {
	this.banneds = banneds;
    }

    public Set<Blog> getBlogs() {
	return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {
	this.blogs = blogs;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
