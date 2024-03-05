package aptech.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.User;
import aptech.dating.model.UserPet;


public interface UserPetRepository extends JpaRepository<UserPet, Integer> {
	List<UserPet> findAllByUserId(int userId);
	List<UserPet> findAllByUserIdAndChooseIsTrue(int userId);
	UserPet findUserPetByUserAndPet_Name(User user, String petName);
}
