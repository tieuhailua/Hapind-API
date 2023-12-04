package aptech.dating.DTO;

import aptech.dating.model.User;
import jakarta.validation.constraints.NotNull;

public class MatchingDTO {
	private Integer id;
	@NotNull(message = "User By Second User must be selected")
	private User userBySecondUserId;
	
	@NotNull(message = "User By First User must be selected")
	private User userByFirstUserId;
	
	private int statusId = 4;

	public MatchingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MatchingDTO(@NotNull(message = "User By Second User must be selected") User userBySecondUserId,
			@NotNull(message = "User By First User must be selected") User userByFirstUserId, int statusId) {
		super();
		this.userBySecondUserId = userBySecondUserId;
		this.userByFirstUserId = userByFirstUserId;
		this.statusId = statusId;
	}

	public User getUserBySecondUserId() {
		return userBySecondUserId;
	}

	public void setUserBySecondUserId(User userBySecondUserId) {
		this.userBySecondUserId = userBySecondUserId;
	}

	public User getUserByFirstUserId() {
		return userByFirstUserId;
	}

	public void setUserByFirstUserId(User userByFirstUserId) {
		this.userByFirstUserId = userByFirstUserId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
}
