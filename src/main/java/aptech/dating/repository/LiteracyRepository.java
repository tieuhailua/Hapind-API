package aptech.dating.repository;

import aptech.dating.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Literacy;


public interface LiteracyRepository extends JpaRepository<Literacy, Integer> {
    public Literacy findLiteracyByUsers(User user);
    public Literacy findLiteracyByName(String name);

}
