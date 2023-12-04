package aptech.dating.DTO;

import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.UserMusic;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MusicDTO {
	private Integer id;
	@NotEmpty(message="Music Name can't be blank")
	private String name;
	
	@NotNull(message = "User Music must be selected")
	private Set<UserMusic> userMusics = new HashSet<UserMusic>(0);

	public MusicDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MusicDTO(@NotEmpty(message = "Music Name can't be blank") String name,
			@NotNull(message = "User Music must be selected") Set<UserMusic> userMusics) {
		super();
		this.name = name;
		this.userMusics = userMusics;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserMusic> getUserMusics() {
		return userMusics;
	}

	public void setUserMusics(Set<UserMusic> userMusics) {
		this.userMusics = userMusics;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
