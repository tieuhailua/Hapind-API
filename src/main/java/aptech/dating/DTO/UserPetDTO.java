package aptech.dating.DTO;

import aptech.dating.model.Pet;
import aptech.dating.model.User;
import jakarta.validation.constraints.NotNull;

public class UserPetDTO {
	private Integer id;
	@NotNull(message = "Pet must be selected")
	private Pet pet;

	@NotNull(message = "User must be selected")
	private User user;

	public UserPetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserPetDTO(@NotNull(message = "Pet must be selected") Pet pet,
			@NotNull(message = "User must be selected") User user) {
		super();
		this.pet = pet;
		this.user = user;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
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
