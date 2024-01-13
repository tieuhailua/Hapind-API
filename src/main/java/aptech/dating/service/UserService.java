package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import aptech.dating.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aptech.dating.DTO.UserDTO;
import aptech.dating.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
	private final UserRepository userRepository;

	// Use constructor-based dependency injection
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
	}

	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Optional<User> getUserByPhone(String phone) {
		return userRepository.findByPhone(phone);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User getRandomUser() {
		return userRepository.findRandomUser();
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

//    @Transactional
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//	User user = userRepository.findByEmail(email)
//		.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
//
//	return UserDetailsImpl.build(user);
//    }
}