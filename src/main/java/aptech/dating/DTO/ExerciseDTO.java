package aptech.dating.DTO;

import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.UserExercise;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ExerciseDTO {
	private Integer id;
	@NotEmpty(message="Exercise Name can't be blank")
	private String name;
	
	@NotNull(message = "User Exercise must be selected")
	private Set<UserExercise> userExercises = new HashSet<UserExercise>(0);

	public ExerciseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExerciseDTO(@NotEmpty(message = "Exercise Name can't be blank") String name,
			@NotNull(message = "User Exercise must be selected") Set<UserExercise> userExercises) {
		super();
		this.name = name;
		this.userExercises = userExercises;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserExercise> getUserExercises() {
		return userExercises;
	}

	public void setUserExercises(Set<UserExercise> userExercises) {
		this.userExercises = userExercises;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
