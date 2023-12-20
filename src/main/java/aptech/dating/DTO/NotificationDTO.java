package aptech.dating.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class NotificationDTO {
	private Integer id;
	
	@NotEmpty(message="Message can't be blank")
	private String message;
	
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date createdAt;
	
	private boolean read = false;

	public NotificationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotificationDTO(
			@NotEmpty(message = "Message can't be blank") String message,
			@NotNull(message = "Created At must be selected") Date createdAt, boolean read) {
		super();
		this.message = message;
		this.createdAt = createdAt;
		this.read = read;
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
