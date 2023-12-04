package aptech.dating.DTO;

import aptech.dating.model.Admin;
import aptech.dating.model.User;
import jakarta.validation.constraints.NotNull;

public class BannedDTO {
	private Integer id;
	@NotNull(message = "Admin must be selected")
	private Admin admin;
	
	@NotNull(message = "User must be selected")
	private User user;

	public BannedDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BannedDTO(@NotNull(message = "Admin must be selected") Admin admin,
			@NotNull(message = "User must be selected") User user) {
		super();
		this.admin = admin;
		this.user = user;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
