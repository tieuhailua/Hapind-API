package aptech.dating.DTO;

import java.util.Date;

import aptech.dating.model.Conversation;
import aptech.dating.model.User;
import jakarta.validation.constraints.NotNull;

public class VideoCallDTO {
	private Integer id;
	@NotNull(message = "Conversation must be selected")
	private Conversation conversation;
	
	@NotNull(message = "User By Receiver must be selected")
	private User userByReceiverId;
	
	@NotNull(message = "User By Caller must be selected")
	private User userByCallerId;
	
	@NotNull(message = "Start Time must be selected")
	private Date startTime;
	
	@NotNull(message = "End Time must be selected")
	private Date endTime;

	public VideoCallDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VideoCallDTO(@NotNull(message = "Conversation must be selected") Conversation conversation,
			@NotNull(message = "User By Receiver must be selected") User userByReceiverId,
			@NotNull(message = "User By Caller must be selected") User userByCallerId,
			@NotNull(message = "Start Time must be selected") Date startTime,
			@NotNull(message = "End Time must be selected") Date endTime) {
		super();
		this.conversation = conversation;
		this.userByReceiverId = userByReceiverId;
		this.userByCallerId = userByCallerId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public User getUserByReceiverId() {
		return userByReceiverId;
	}

	public void setUserByReceiverId(User userByReceiverId) {
		this.userByReceiverId = userByReceiverId;
	}

	public User getUserByCallerId() {
		return userByCallerId;
	}

	public void setUserByCallerId(User userByCallerId) {
		this.userByCallerId = userByCallerId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
