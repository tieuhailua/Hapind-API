package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.Pet;


public interface PetRepository extends JpaRepository<Pet, Integer> {
	Pet findPetByName(String name);
}
