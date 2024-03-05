package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Singer;


public interface SingerRepository extends JpaRepository<Singer, Integer> {
	Singer findSingerByName(String name);
}
