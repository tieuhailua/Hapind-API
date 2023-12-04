package aptech.dating.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.Message;
import aptech.dating.model.UserConversation;
import aptech.dating.model.VideoCall;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ConversationDTO {
	private Integer id;
	@NotEmpty(message="Type can't be blank")
	private String type;
	
	@NotNull(message = "Create date must be selected")
	private Date createdAt;
	
	@NotNull(message = "Message must be selected")
	private Set<Message> messages = new HashSet<Message>(0);
	
	@NotNull(message = "Video Call must be selected")
	private Set<VideoCall> videoCalls = new HashSet<VideoCall>(0);
	
	@NotNull(message = "User Conversation must be selected")
	private Set<UserConversation> userConversations = new HashSet<UserConversation>(0);

	public ConversationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConversationDTO(@NotEmpty(message = "Type can't be blank") String type,
			@NotNull(message = "Create date must be selected") Date createdAt,
			@NotNull(message = "Message must be selected") Set<Message> messages,
			@NotNull(message = "Video Call must be selected") Set<VideoCall> videoCalls,
			@NotNull(message = "User Conversation must be selected") Set<UserConversation> userConversations) {
		super();
		this.type = type;
		this.createdAt = createdAt;
		this.messages = messages;
		this.videoCalls = videoCalls;
		this.userConversations = userConversations;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<VideoCall> getVideoCalls() {
		return videoCalls;
	}

	public void setVideoCalls(Set<VideoCall> videoCalls) {
		this.videoCalls = videoCalls;
	}

	public Set<UserConversation> getUserConversations() {
		return userConversations;
	}

	public void setUserConversations(Set<UserConversation> userConversations) {
		this.userConversations = userConversations;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
