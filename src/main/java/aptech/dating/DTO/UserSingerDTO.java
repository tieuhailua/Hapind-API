package aptech.dating.DTO;

import aptech.dating.model.Singer;
import aptech.dating.model.User;
import jakarta.validation.constraints.NotNull;

public class UserSingerDTO {
	private Integer id;
	@NotNull(message = "Singer must be selected")
	private Singer singer;
	
	@NotNull(message = "User must be selected")
	private User user;

	private boolean choose;
	
	public UserSingerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserSingerDTO(@NotNull(message = "Singer must be selected") Singer singer,
			@NotNull(message = "User must be selected") User user, boolean choose) {
		super();
		this.singer = singer;
		this.user = user;
		this.choose = choose;
	}

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
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

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}
	
	
}
