package aptech.dating.DTO;

import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.UserLanguage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LanguageDTO {
	private Integer id;
	@NotEmpty(message="Language Name can't be blank")
	private String name;
	
	@NotNull(message = "User Language must be selected")
	private Set<UserLanguage> userLanguages = new HashSet<UserLanguage>(0);

	public LanguageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LanguageDTO(@NotEmpty(message = "Language Name can't be blank") String name,
			@NotNull(message = "User Language must be selected") Set<UserLanguage> userLanguages) {
		super();
		this.name = name;
		this.userLanguages = userLanguages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserLanguage> getUserLanguages() {
		return userLanguages;
	}

	public void setUserLanguages(Set<UserLanguage> userLanguages) {
		this.userLanguages = userLanguages;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
