package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.MatchingDTO;
import aptech.dating.model.Matching;
import aptech.dating.model.User;
import aptech.dating.repository.MatchingRepository;
import jakarta.transaction.Transactional;

@Service
public class MatchingService {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
	private final MatchingRepository matchingRepository;

	// Use constructor-based dependency injection
	@Autowired
	public MatchingService(MatchingRepository matchingRepository) {
		this.matchingRepository = matchingRepository;
	}

	public List<User> getMatchingUser(Integer loginId) {
		return matchingRepository.findMatchingUser(loginId);
	}

	public List<User> getChatUser(Integer loginId) {
		return matchingRepository.findChatUser(loginId);
	}
	
    @Transactional
	public Matching updateStatus(int firstUserId, int secondUserId, int newStatus) {
		Matching matching = matchingRepository.findByUserIds(firstUserId, secondUserId)
				.get();
		if (matching != null) {
			matching.setStatusId(newStatus);
			matchingRepository.save(matching);
			return matching;
		}
		return null;
	}
    
    @Transactional
	public Matching updateBlockStatus(int firstUserId, int secondUserId, int newStatus) {
		Matching matching = matchingRepository.findByUserIds(firstUserId, secondUserId)
				.get();
		if (matching != null) {
			matching.setStatusId(newStatus);
			matchingRepository.save(matching);
			return matching;
		}
		return null;
	}

	public List<Matching> getAllMatchings() {
		return matchingRepository.findAll();
	}

	public Optional<Matching> getMatchingById(int id) {
		return matchingRepository.findById(id);
	}

	public Matching saveMatching(Matching matching) {
		return matchingRepository.save(matching);
	}

	public void deleteMatching(int id) {
		matchingRepository.deleteById(id);
	}

	public MatchingDTO getMatching(int id) {
		Matching matching = this.matchingRepository.findById(id).get();
		MatchingDTO matchingDto = this.modelMapper.map(matching, MatchingDTO.class);
		return matchingDto;
	}
}
