package aptech.dating.DTO;

import aptech.dating.model.User;
import jakarta.validation.constraints.NotEmpty;

public class UserImageDTO {
	private Integer id;
	private User user;
	
	@NotEmpty(message="Image Path can't be blank")
	private String imagePath;

	public UserImageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserImageDTO(User user,
			@NotEmpty(message = "Image Path can't be blank") String imagePath) {
		super();
		this.user = user;
		this.imagePath = imagePath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
