package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.AdminDTO;
import aptech.dating.DTO.BannedDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.User;
import aptech.dating.repository.BannedRepository;

@Service
public class BannedService {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the repository as final to ensure its immutability
	private final BannedRepository bannedRepository;

	// Use constructor-based dependency injection
	@Autowired
	public BannedService(BannedRepository bannedRepository) {
		this.bannedRepository = bannedRepository;
	}

	public List<Banned> getAllBanneds() {
		return bannedRepository.findAll();
	}

	public Optional<Banned> getBannedById(int id) {
		return bannedRepository.findById(id);
	}

	public Optional<Banned> getByAdminIdAndUserId(int adminId, int userId) {
		return bannedRepository.findByAdminIdAndUserId(adminId, userId);
	}

	public Optional<Admin> getAdminById(int id) {
		return bannedRepository.findByAdminId(id);
	}

	public Optional<User> getUserById(int id) {
		return bannedRepository.findByUserId(id);
	}

	public Banned saveBanned(Banned banned) {
		return bannedRepository.save(banned);
	}

	public void deleteBanned(int id) {
		bannedRepository.deleteById(id);
	}

	public BannedDTO getBanned(int id) {
		Banned banned = this.bannedRepository.findById(id).get();
		BannedDTO bannedDto = this.modelMapper.map(banned, BannedDTO.class);
		return bannedDto;
	}
}
