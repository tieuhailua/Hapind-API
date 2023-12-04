package aptech.dating.DTO;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class NotificationDTO {
	private Integer id;
	@NotNull(message = "Use Id must be selected")
	private int useId;
	@NotEmpty(message="Message can't be blank")
	
	private String message;
	
	@NotNull(message = "Create At must be selected")
	private Date createdAt;
	
	private boolean read = false;

	public NotificationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotificationDTO(@NotNull(message = "Use Id must be selected") int useId,
			@NotEmpty(message = "Message can't be blank") String message,
			@NotNull(message = "Create At must be selected") Date createdAt, boolean read) {
		super();
		this.useId = useId;
		this.message = message;
		this.createdAt = createdAt;
		this.read = read;
	}

	public int getUseId() {
		return useId;
	}

	public void setUseId(int useId) {
		this.useId = useId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
