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
	
	private boolean choose;
	public UserExerciseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserExerciseDTO(@NotNull(message = "Exercise must be selected") Exercise exercise,
			@NotNull(message = "User must be selected") User user, boolean choose) {
		super();
		this.exercise = exercise;
		this.user = user;
		this.choose = choose;
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

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}
	
	
}
