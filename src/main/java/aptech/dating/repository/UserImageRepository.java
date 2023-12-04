package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.UserImage;


public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
}
