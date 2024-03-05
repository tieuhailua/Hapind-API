package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import aptech.dating.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.DrinkingDTO;
import aptech.dating.model.Drinking;
import aptech.dating.model.Purpose;
import aptech.dating.repository.DrinkingRepository;

@Service
public class DrinkingService {
	@Autowired
    private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
    private final DrinkingRepository drinkingRepository;

    // Use constructor-based dependency injection
    @Autowired
    public DrinkingService(DrinkingRepository drinkingRepository) {
        this.drinkingRepository = drinkingRepository;
    }

    public List<Drinking> getAllDrinkings() {
        return drinkingRepository.findAll();
    }

    public Optional<Drinking> getDrinkingById(int id) {
        return drinkingRepository.findById(id);
    }
    
    public Drinking getDrinkingByName(String name) {
		return drinkingRepository.findDrinkingByName(name);
	}
    
    public Drinking getDrinkingByUserId(User user) {
        return drinkingRepository.findDrinkingByUsers(user);
    }

    public Drinking saveDrinking(Drinking drinking) {
        return drinkingRepository.save(drinking);
    }

    public void deleteDrinking(int id) {
    	drinkingRepository.deleteById(id);
    }
    
    public DrinkingDTO getDrinking(int id) { 
        Drinking drinking = this.drinkingRepository.findById(id).get(); 
        DrinkingDTO drinkingDto = this.modelMapper.map(drinking, DrinkingDTO.class); 
        return drinkingDto; 
    } 
}


