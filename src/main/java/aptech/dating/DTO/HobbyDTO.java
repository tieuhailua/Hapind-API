package aptech.dating.DTO;

import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.UserHobby;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class HobbyDTO {
	private Integer id;
	@NotEmpty(message="Hobby Name can't be blank")
	private String name;
	
	@NotNull(message = "User Hobby must be selected")
	private Set<UserHobby> userHobbies = new HashSet<UserHobby>(0);

	public HobbyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HobbyDTO(@NotEmpty(message = "Hobby Name can't be blank") String name,
			@NotNull(message = "User Hobby must be selected") Set<UserHobby> userHobbies) {
		super();
		this.name = name;
		this.userHobbies = userHobbies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserHobby> getUserHobbies() {
		return userHobbies;
	}

	public void setUserHobbies(Set<UserHobby> userHobbies) {
		this.userHobbies = userHobbies;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
