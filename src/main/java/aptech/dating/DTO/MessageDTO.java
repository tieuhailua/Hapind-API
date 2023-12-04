package aptech.dating.DTO;

import java.util.Date;

import aptech.dating.model.Conversation;
import aptech.dating.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MessageDTO {
	private Integer id;
	@NotNull(message = "Conversation must be selected")
	private Conversation conversation;
	
	@NotNull(message = "USer must be selected")
	private User user;
	
	@NotEmpty(message="Content Name can't be blank")
	private String content;
	
	@NotNull(message = "Sent At must be selected")
	private Date sentAt;

	public MessageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageDTO(@NotNull(message = "Conversation must be selected") Conversation conversation,
			@NotNull(message = "USer must be selected") User user,
			@NotEmpty(message = "Content Name can't be blank") String content,
			@NotNull(message = "Sent At must be selected") Date sentAt) {
		super();
		this.conversation = conversation;
		this.user = user;
		this.content = content;
		this.sentAt = sentAt;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSentAt() {
		return sentAt;
	}

	public void setSentAt(Date sentAt) {
		this.sentAt = sentAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
