package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Report;


public interface ReportRepository extends JpaRepository<Report, Integer> {
	public List<Report> findByUserByReporterId_Id(Integer reporterId);

}
