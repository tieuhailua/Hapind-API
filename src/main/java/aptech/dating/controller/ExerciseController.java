package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aptech.dating.DTO.ExerciseDTO;
import aptech.dating.DTO.FamilyDTO;
import aptech.dating.model.Banned;
import aptech.dating.model.Exercise;
import aptech.dating.model.Family;
import aptech.dating.service.ExerciseService;

@RestController
@RequestMapping("/api/exercise")
@CrossOrigin(origins = "http://localhost:4200")
public class ExerciseController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final ExerciseService exerciseService;

	// Use constructor-based dependency injection
	@Autowired
	public ExerciseController(ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}

	@GetMapping
	public ResponseEntity<List<ExerciseDTO>> getAllExercises() {
		List<Exercise> exercise = exerciseService.getAllExercises();

		List<ExerciseDTO> exerciseDTO = exercise.stream().map(element -> modelMapper.map(element, ExerciseDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(exerciseDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable int id) {
		Optional<Exercise> exercise = exerciseService.getExerciseById(id);

		ExerciseDTO exerciseDTO = modelMapper.map(exercise, ExerciseDTO.class);
		
		return exerciseDTO!=null?ResponseEntity.ok(exerciseDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Exercise> createEvidence(@RequestBody @Validated ExerciseDTO exerciseDTO) {
		Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);
		return ResponseEntity.ok(exerciseService.saveExercise(exercise));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Exercise> updateExercise(@PathVariable int id, @RequestBody @Validated ExerciseDTO exerciseDTO) {
		Optional<Exercise> exercise = exerciseService.getExerciseById(id);

	    if (exercise.isPresent()) {
	    	Exercise updateExercise = exercise.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(exerciseDTO, updateExercise);

	        // Save the updated admin
	        return ResponseEntity.ok(exerciseService.saveExercise(updateExercise));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteExercise(@PathVariable int id) {
		exerciseService.deleteExercise(id);
		return ResponseEntity.ok().build();
	}
}





