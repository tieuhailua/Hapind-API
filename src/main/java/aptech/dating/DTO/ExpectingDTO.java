package aptech.dating.DTO;

import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.UserExpecting;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ExpectingDTO {
	private Integer id;
	@NotEmpty(message="Expecting Name can't be blank")
	private String name;
	
	@NotNull(message = "User Expecting must be selected")
	private Set<UserExpecting> userExpectings = new HashSet<UserExpecting>(0);

	public ExpectingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExpectingDTO(@NotEmpty(message = "Expecting Name can't be blank") String name,
			@NotNull(message = "User Expecting must be selected") Set<UserExpecting> userExpectings) {
		super();
		this.name = name;
		this.userExpectings = userExpectings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserExpecting> getUserExpectings() {
		return userExpectings;
	}

	public void setUserExpectings(Set<UserExpecting> userExpectings) {
		this.userExpectings = userExpectings;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
