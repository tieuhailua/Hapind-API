package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Music;


public interface MusicRepository extends JpaRepository<Music, Integer> {
    Music findMusicByName(String name);
}
