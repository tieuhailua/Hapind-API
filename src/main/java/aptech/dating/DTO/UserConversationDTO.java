package aptech.dating.DTO;

import aptech.dating.model.Conversation;
import aptech.dating.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserConversationDTO {
	private Integer id;
	@NotNull(message = "Conversation must be selected")
	private Conversation conversation;
	
	@NotNull(message = "User must be selected")
	private User user;
	
	@NotEmpty(message="Role can't be blank")
	private String role;

	public UserConversationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserConversationDTO(@NotNull(message = "Conversation must be selected") Conversation conversation,
			@NotNull(message = "User must be selected") User user,
			@NotEmpty(message = "Role can't be blank") String role) {
		super();
		this.conversation = conversation;
		this.user = user;
		this.role = role;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
