package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import aptech.dating.DTO.UserDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Family;
import aptech.dating.model.Report;
import aptech.dating.model.Status;
import aptech.dating.model.User;
import aptech.dating.service.AdminService;
import aptech.dating.service.BannedService;
import aptech.dating.service.ReasonService;
import aptech.dating.service.ReportService;
import aptech.dating.service.StatusService;
import aptech.dating.service.UserService;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final ReportService reportService;
	private final BannedService bannedService;
	private final UserService userService;
	private final AdminService adminService;
	private final ReasonService reasonService;
	private final StatusService statusService;

	// Use constructor-based dependency injection
	@Autowired
	public ReportController(ReportService reportService, BannedService bannedService, UserService userService,
			AdminService adminService, ReasonService reasonService, StatusService statusService) {
		super();
		this.reportService = reportService;
		this.bannedService = bannedService;
		this.userService = userService;
		this.adminService = adminService;
		this.reasonService = reasonService;
		this.statusService = statusService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<ReportDTO>> getAllReports() {
		List<Report> report = reportService.getAllReports();

		List<ReportDTO> reportDTO = report.stream().map(element -> modelMapper.map(element, ReportDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(reportDTO);
	}

	@GetMapping("/censorship/{status}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable String status) {
		User user = userService.getRandomUserForCensorship(status);

		UserDTO userDTO = modelMapper.map(user, UserDTO.class);

		return userDTO != null ? ResponseEntity.ok(userDTO) : ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<ReportDTO> getReportById(@PathVariable int id) {
		Optional<Report> report = reportService.getReportById(id);

		ReportDTO reportDTO = modelMapper.map(report, ReportDTO.class);

		return reportDTO != null ? ResponseEntity.ok(reportDTO) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Report> createReport(@RequestBody @Validated ReportDTO reportDTO) {
		Report report = modelMapper.map(reportDTO, Report.class);
		return ResponseEntity.ok(reportService.saveReport(report));
	}

	@GetMapping("/censor/{userId}/{adminUserName}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<User> processingMod(@PathVariable int userId, @PathVariable String adminUserName) {
		Optional<User> user = userService.getUserById(userId);
		if (user.isPresent()) {
			User updateUser = user.get();
			updateUser.setStatus(statusService.getStatusByName("Banned").get());
			bannedService.saveBanned(new Banned(adminService.getAdminByUsername(adminUserName).get(), updateUser,
					reasonService.getReasonByName("Inappropriate Images")));

			userService.saveUser(updateUser);
			return ResponseEntity.ok(updateUser);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{reportId}/{status}/{adminUserName}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Report> processingReport(@PathVariable int reportId, @PathVariable String status,
			@PathVariable String adminUserName) {
		Optional<Report> report = reportService.getReportById(reportId);
		if (report.isPresent()) {
			Report updateReport = report.get();
			updateReport.setStatus(status);
			int adminId = adminService.getAdminByUsername(adminUserName).get().getId();
			if (bannedService.getByAdminIdAndUserId(adminId, updateReport.getUserByReportedId().getId()) == null) {
				bannedService.saveBanned(new Banned(adminService.getAdminById(adminId).get(),
						updateReport.getUserByReportedId(), updateReport.getReason()));
			}
			// User updateUser = userService.getUserById(reportDTO.getId());
			User user = userService.getUserById(updateReport.getUserByReportedId().getId()).get();
			user.setStatus(statusService.getStatusByName("Banned").get());
			userService.saveUser(user);
			reportService.saveReport(updateReport);
			return ResponseEntity.ok(updateReport);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
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
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> deleteReport(@PathVariable int id) {
		reportService.deleteReport(id);
		return ResponseEntity.ok().build();
	}
}