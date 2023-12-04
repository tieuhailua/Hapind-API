package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Report;


public interface ReportRepository extends JpaRepository<Report, Integer> {
}
