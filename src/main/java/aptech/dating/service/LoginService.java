package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.AdminDTO;
import aptech.dating.DTO.UserDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.User;
import aptech.dating.repository.AdminRepository;
import aptech.dating.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public LoginService(UserRepository userRepository, AdminRepository adminRepository) {
	this.userRepository = userRepository;
	this.adminRepository = adminRepository;
    }

    public List<User> getAllUsers() {
	return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
	return userRepository.findById(id);
    }

    public User saveUser(User user) {
	return userRepository.save(user);
    }

    public void signUpUser(@Param("email") String email, @Param("password") String password) {
	userRepository.signUp(email, password);
    }

    public void banUser(int statusId, int userId) {
	userRepository.banUser(statusId, userId);
    }

    public void deleteUser(int id) {
	userRepository.deleteById(id);
    }

    public UserDTO getUser(int id) {
	User user = this.userRepository.findById(id).get();
	UserDTO userDto = this.modelMapper.map(user, UserDTO.class);
	return userDto;
    }

    public List<Admin> getAllAdmins() {
	return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(int id) {
	return adminRepository.findById(id);
    }

    public Admin saveAdmin(Admin admin) {
	return adminRepository.save(admin);
    }

    public void deleteAdmin(int id) {
	adminRepository.deleteById(id);
    }

    public AdminDTO getAdmin(int id) {
	Admin admin = this.adminRepository.findById(id).get();
	AdminDTO adminDto = this.modelMapper.map(admin, AdminDTO.class);
	return adminDto;
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findByUsername(username);
        Optional<User> userEmailOptional = userRepository.findByEmail(username);
        Optional<User> userPhoneOptional = userRepository.findByPhone(username);
        
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            return AdminDetailsImpl.build(admin);
        } else if (userEmailOptional.isPresent()) {
            User user = userEmailOptional.get();
            return UserDetailsImpl.build(user);
        }else if (userPhoneOptional.isPresent()) {
            User user = userPhoneOptional.get();
            return UserDetailsImpl.build(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }


}
