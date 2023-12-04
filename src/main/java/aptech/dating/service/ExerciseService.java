package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.ExerciseDTO;
import aptech.dating.model.Exercise;
import aptech.dating.repository.ExerciseRepository;

@Service
public class ExerciseService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final ExerciseRepository exerciseRepository;

    // Use constructor-based dependency injection
    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> getExerciseById(int id) {
        return exerciseRepository.findById(id);
    }

    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(int id) {
    	exerciseRepository.deleteById(id);
    }
    
    public ExerciseDTO getExercise(int id) { 
       Exercise exercise = this.exerciseRepository.findById(id).get(); 
       ExerciseDTO exerciseDto = this.modelMapper.map(exercise, ExerciseDTO.class); 
        return exerciseDto; 
    } 
}




