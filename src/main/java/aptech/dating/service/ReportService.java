package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.ReportDTO;
import aptech.dating.model.Report;
import aptech.dating.repository.ReportRepository;

@Service
public class ReportService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final ReportRepository reportRepository;

    // Use constructor-based dependency injection
    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
    
    public List<Report> getByUserByReporterId_Id(int id) {
        return reportRepository.findByUserByReporterId_Id(id);
    }
    
    public Optional<Report> getReportById(int id) {
        return reportRepository.findById(id);
    }

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    public void deleteReport(int id) {
        reportRepository.deleteById(id);
    }
    
    public ReportDTO getReport(int id) { 
        Report report = this.reportRepository.findById(id).get(); 
        ReportDTO reportDto = this.modelMapper.map(report, ReportDTO.class); 
        return reportDto; 
    } 
}