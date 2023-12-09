package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.UserLanguage;



public interface UserLanguageRepository extends JpaRepository<UserLanguage, Integer> {
	List<UserLanguage> findAllByUserId(int userId);
}
