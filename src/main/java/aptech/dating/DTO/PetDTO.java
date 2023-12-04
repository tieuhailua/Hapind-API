package aptech.dating.DTO;

import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.UserPet;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PetDTO {
	private Integer id;
	@NotEmpty(message="Pet Name can't be blank")
	private String name;
	
	@NotNull(message = "User Pet must be selected")
	private Set<UserPet> userPets = new HashSet<UserPet>(0);

	public PetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PetDTO(@NotEmpty(message = "Pet Name can't be blank") String name,
			@NotNull(message = "User Pet must be selected") Set<UserPet> userPets) {
		super();
		this.name = name;
		this.userPets = userPets;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserPet> getUserPets() {
		return userPets;
	}

	public void setUserPets(Set<UserPet> userPets) {
		this.userPets = userPets;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
