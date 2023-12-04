package aptech.dating.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.Evidence;
import aptech.dating.model.Reason;
import aptech.dating.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ReportDTO {
	private Integer id;
	@NotNull(message="Reason must be selected")
	private Reason reason;
	
	@NotNull(message="User By Reported must be selected")
	private User userByReportedId;
	
	@NotNull(message="User By Reporter must be selected")
	private User userByReporterId;
	
	@NotEmpty(message="Description can't be blank")
	private String description;
	
	@NotNull(message="Create At must be selected")
	private Date createdAt;
	
	@NotNull(message="Evidence must be selected")
	private Set<Evidence> evidences = new HashSet<Evidence>(0);

	public ReportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReportDTO(@NotNull(message = "Reason must be selected") Reason reason,
			@NotNull(message = "User By Reported must be selected") User userByReportedId,
			@NotNull(message = "User By Reporter must be selected") User userByReporterId,
			@NotEmpty(message = "Description can't be blank") String description,
			@NotNull(message = "Create At must be selected") Date createdAt,
			@NotNull(message = "Evidence must be selected") Set<Evidence> evidences) {
		super();
		this.reason = reason;
		this.userByReportedId = userByReportedId;
		this.userByReporterId = userByReporterId;
		this.description = description;
		this.createdAt = createdAt;
		this.evidences = evidences;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public User getUserByReportedId() {
		return userByReportedId;
	}

	public void setUserByReportedId(User userByReportedId) {
		this.userByReportedId = userByReportedId;
	}

	public User getUserByReporterId() {
		return userByReporterId;
	}

	public void setUserByReporterId(User userByReporterId) {
		this.userByReporterId = userByReporterId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Set<Evidence> getEvidences() {
		return evidences;
	}

	public void setEvidences(Set<Evidence> evidences) {
		this.evidences = evidences;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
