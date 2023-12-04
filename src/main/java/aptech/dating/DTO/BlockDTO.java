package aptech.dating.DTO;

import aptech.dating.model.User;
import jakarta.validation.constraints.NotNull;

public class BlockDTO {
	private Integer id;
	@NotNull(message="User By Blocked Id must be selected")
	private User userByBlockedId;
	
	@NotNull(message="User By Use Id must be selected")
	private User userByUseId;

	public BlockDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlockDTO(@NotNull(message = "User By Blocked Id must be selected") User userByBlockedId,
			@NotNull(message = "User By Use Id must be selected") User userByUseId) {
		super();
		this.userByBlockedId = userByBlockedId;
		this.userByUseId = userByUseId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserByBlockedId() {
		return userByBlockedId;
	}

	public void setUserByBlockedId(User userByBlockedId) {
		this.userByBlockedId = userByBlockedId;
	}

	public User getUserByUseId() {
		return userByUseId;
	}

	public void setUserByUseId(User userByUseId) {
		this.userByUseId = userByUseId;
	}
	
	
}
