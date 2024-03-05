package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Language;


public interface LanguageRepository extends JpaRepository<Language, Integer> {
	Language findLanguageByName(String name);
}
