package aptech.dating.DTO;

import aptech.dating.model.Music;
import aptech.dating.model.User;
import jakarta.validation.constraints.NotNull;

public class UserMusicDTO {
	private Integer id;
	@NotNull(message = "Music must be selected")
	private Music music;

	@NotNull(message = "User must be selected")
	private User user;

	public UserMusicDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserMusicDTO(@NotNull(message = "Music must be selected") Music music,
			@NotNull(message = "User must be selected") User user) {
		super();
		this.music = music;
		this.user = user;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
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
