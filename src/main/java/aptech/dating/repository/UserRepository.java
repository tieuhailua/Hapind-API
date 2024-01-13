package aptech.dating.repository;

import aptech.dating.model.*;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query("Update User u set u.status.id = :status where u.id = :id")
    public void banUser(@Param("status") Integer status, @Param("id") Integer id);
    public Optional<User> findByEmail(String email);
    public Optional<User> findByPhone(String phone);
    @Transactional
    @Modifying
    @Query("INSERT INTO User (email, password) VALUES (:email, :password)")
    public void signUp(@Param("email") String email, @Param("password") String password);
    
    @Transactional
    @Query(value = "SELECT * FROM \"user\" WHERE id < 4 ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    public User findRandomUser();
}
