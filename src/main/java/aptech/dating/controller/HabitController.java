package aptech.dating.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aptech.dating.DTO.FamilyDTO;
import aptech.dating.DTO.HabitDTO;
import aptech.dating.model.Banned;
import aptech.dating.model.Expecting;
import aptech.dating.model.Family;
import aptech.dating.model.Habit;
import aptech.dating.service.HabitService;

@RestController
@RequestMapping("/api/habit")
public class HabitController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final HabitService habitService;

	// Use constructor-based dependency injection
	@Autowired
	public HabitController(HabitService habitService) {
		this.habitService = habitService;
	}

	@GetMapping
	public ResponseEntity<List<HabitDTO>> getAllHabits() {
		List<Habit> habit = habitService.getAllHabits();

		List<HabitDTO> habitDTO = habit.stream().map(element -> modelMapper.map(element, HabitDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(habitDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<HabitDTO> getHabitById(@PathVariable int id) {
		Optional<Habit> habit = habitService.getHabitById(id);

		HabitDTO habitDTO = modelMapper.map(habit, HabitDTO.class);
		
		return habitDTO!=null?ResponseEntity.ok(habitDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Habit> createHabit(@RequestBody @Validated HabitDTO habitDTO) {
		Habit habit = modelMapper.map(habitDTO, Habit.class);
		return ResponseEntity.ok(habitService.saveHabit(habit));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Habit> updateHabit(@PathVariable int id, @RequestBody @Validated HabitDTO habitDTO) {
		Optional<Habit> habit = habitService.getHabitById(id);

	    if (habit.isPresent()) {
	    	Habit updateHabit = habit.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(habitDTO, updateHabit);

	        // Save the updated admin
	        return ResponseEntity.ok(habitService.saveHabit(updateHabit));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteHabit(@PathVariable int id) {
		habitService.deleteHabit(id);
		return ResponseEntity.ok().build();
	}
}







