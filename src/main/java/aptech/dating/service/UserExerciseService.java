package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.UserExerciseDTO;
import aptech.dating.model.UserExercise;
import aptech.dating.model.UserMusic;
import aptech.dating.repository.UserExerciseRepository;

@Service
public class UserExerciseService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final UserExerciseRepository userExerciseRepository;

    // Use constructor-based dependency injection
    @Autowired
    public UserExerciseService(UserExerciseRepository userExerciseRepository) {
        this.userExerciseRepository = userExerciseRepository;
    }

    public List<UserExercise> getAllUserExercises() {
        return userExerciseRepository.findAll();
    }

    public Optional<UserExercise> getUserExerciseById(int id) {
        return userExerciseRepository.findById(id);
    }
    
    public List<UserExercise> getUserExerciesByUserId(int userId) {
        return userExerciseRepository.findAllByUserId(userId);
    }

    public UserExercise saveUserExercise(UserExercise userExercise) {
        return userExerciseRepository.save(userExercise);
    }

    public void deleteUserExercise(int id) {
        userExerciseRepository.deleteById(id);
    }
    
    public UserExerciseDTO getUserExercise(int id) { 
        UserExercise userExercise = this.userExerciseRepository.findById(id).get(); 
        UserExerciseDTO userExerciseDto = this.modelMapper.map(userExercise, UserExerciseDTO.class); 
        return userExerciseDto; 
    } 
}