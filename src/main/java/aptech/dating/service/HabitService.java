package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.HabitDTO;
import aptech.dating.model.Habit;
import aptech.dating.repository.HabitRepository;

@Service
public class HabitService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final HabitRepository habitRepository;

    // Use constructor-based dependency injection
    @Autowired
    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    public Optional<Habit> getHabitById(int id) {
        return habitRepository.findById(id);
    }

    public Habit saveHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public void deleteHabit(int id) {
    	habitRepository.deleteById(id);
    }
    
    public HabitDTO getHabit(int id) { 
        Habit habit = this.habitRepository.findById(id).get(); 
        HabitDTO habitDto = this.modelMapper.map(habit, HabitDTO.class); 
        return habitDto; 
    } 
}







