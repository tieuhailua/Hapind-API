package aptech.dating.DTO;

import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.UserSinger;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SingerDTO {
	private Integer id;
	@NotEmpty(message="Singer Name can't be blank")
	private String name;
	
	@NotNull(message = "User Singer must be selected")
	private Set<UserSinger> userSingers = new HashSet<UserSinger>(0);

	public SingerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SingerDTO(@NotEmpty(message = "Singer Name can't be blank") String name,
			@NotNull(message = "User Singer must be selected") Set<UserSinger> userSingers) {
		super();
		this.name = name;
		this.userSingers = userSingers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserSinger> getUserSingers() {
		return userSingers;
	}

	public void setUserSingers(Set<UserSinger> userSingers) {
		this.userSingers = userSingers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
