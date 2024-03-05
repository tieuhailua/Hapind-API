package aptech.dating.DTO;

import jakarta.validation.constraints.NotEmpty;

public class ChoiceDTO {
	private Integer id;
	private String name;
	
	public ChoiceDTO() {
		super();
	}

	public ChoiceDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
