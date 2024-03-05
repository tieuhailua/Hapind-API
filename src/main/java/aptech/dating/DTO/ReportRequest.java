package aptech.dating.DTO;

import java.util.List;

public class ReportRequest {
    private String description;
    private List<String> evidens;
    
	public ReportRequest() {
		super();
	}

	public ReportRequest(String description, List<String> evidens) {
		super();
		this.description = description;
		this.evidens = evidens;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getEvidens() {
		return evidens;
	}

	public void setEvidens(List<String> evidens) {
		this.evidens = evidens;
	}
    

}
