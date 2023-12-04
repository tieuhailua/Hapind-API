package aptech.dating.DTO;

import aptech.dating.model.Exercise;
import aptech.dating.model.User;
import jakarta.validation.constraints.NotNull;

public class UserExerciseDTO {
	private Integer id;
	@NotNull(message = "Exercise must be selected")
	private Exercise exercise;
	
	@NotNull(message = "User must be selected")
	private User user;

	public UserExerciseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserExerciseDTO(@NotNull(message = "Exercise must be selected") Exercise exercise,
			@NotNull(message = "User must be selected") User user) {
		super();
		this.exercise = exercise;
		this.user = user;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
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
