package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aptech.dating.DTO.FamilyDTO;
import aptech.dating.DTO.ReportDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Report;
import aptech.dating.service.ReportService;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final ReportService reportService;

	// Use constructor-based dependency injection
	@Autowired
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}

	@GetMapping
	public ResponseEntity<List<ReportDTO>> getAllReports() {
		List<Report> report = reportService.getAllReports();

		List<ReportDTO> reportDTO = report.stream().map(element -> modelMapper.map(element, ReportDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(reportDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReportDTO> getReportById(@PathVariable int id) {
		Optional<Report> report = reportService.getReportById(id);

		ReportDTO reportDTO = modelMapper.map(report, ReportDTO.class);
		
		return reportDTO!=null?ResponseEntity.ok(reportDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Report> createReport(@RequestBody @Validated ReportDTO reportDTO) {
		Report report = modelMapper.map(reportDTO, Report.class);
		return ResponseEntity.ok(reportService.saveReport(report));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Report> updateReport(@PathVariable int id, @RequestBody @Validated ReportDTO reportDTO) {
		Optional<Report> report = reportService.getReportById(id);

	    if (report.isPresent()) {
	    	Report updateReport = report.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(reportDTO, updateReport);

	        // Save the updated admin
	        return ResponseEntity.ok(reportService.saveReport(updateReport));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReport(@PathVariable int id) {
		reportService.deleteReport(id);
		return ResponseEntity.ok().build();
	}
}