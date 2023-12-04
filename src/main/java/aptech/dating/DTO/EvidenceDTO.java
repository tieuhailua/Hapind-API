package aptech.dating.DTO;

import aptech.dating.model.Report;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EvidenceDTO {
	private Integer id;
	@NotNull(message = "Report must be selected")
	private Report report;
	
	@NotEmpty(message="Image can't be blank")
	private String image;

	public EvidenceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EvidenceDTO(@NotNull(message = "Report must be selected") Report report,
			@NotEmpty(message = "Image can't be blank") String image) {
		super();
		this.report = report;
		this.image = image;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
