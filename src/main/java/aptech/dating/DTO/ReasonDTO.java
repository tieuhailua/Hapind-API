package aptech.dating.DTO;

import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.Report;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ReasonDTO {
	private Integer id;
	@NotEmpty(message="Reason Name can't be blank")
	private String name;
	
	@NotNull(message = "Report must be selected")
	private Set<Report> reports = new HashSet<Report>(0);

	public ReasonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReasonDTO(@NotEmpty(message = "Reason Name can't be blank") String name,
			@NotNull(message = "Report must be selected") Set<Report> reports) {
		super();
		this.name = name;
		this.reports = reports;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Report> getReports() {
		return reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
