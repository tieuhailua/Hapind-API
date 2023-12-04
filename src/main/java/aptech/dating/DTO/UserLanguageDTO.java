package aptech.dating.DTO;

import aptech.dating.model.Language;
import aptech.dating.model.User;
import jakarta.validation.constraints.NotNull;

public class UserLanguageDTO {
	private Integer id;
	@NotNull(message = "Language must be selected")
	private Language language;
	
	@NotNull(message = "User must be selected")
	private User user;
	private boolean choose;
	public UserLanguageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLanguageDTO(@NotNull(message = "Language must be selected") Language language,
			@NotNull(message = "User must be selected") User user, boolean choose) {
		super();
		this.language = language;
		this.user = user;
		this.choose = choose;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
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
