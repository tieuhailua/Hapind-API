package aptech.dating.DTO;

import aptech.dating.model.Expecting;
import aptech.dating.model.User;
import jakarta.validation.constraints.NotNull;

public class UserExpectingDTO {
	private Integer id;
	@NotNull(message = "Expecting must be selected")
	private Expecting expecting;
	
	@NotNull(message = "User must be selected")
	private User user;

	public UserExpectingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserExpectingDTO(@NotNull(message = "Expecting must be selected") Expecting expecting,
			@NotNull(message = "User must be selected") User user) {
		super();
		this.expecting = expecting;
		this.user = user;
	}

	public Expecting getExpecting() {
		return expecting;
	}

	public void setExpecting(Expecting expecting) {
		this.expecting = expecting;
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
