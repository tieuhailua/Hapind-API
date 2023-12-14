package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aptech.dating.DTO.AdminDTO;
import aptech.dating.model.Admin;
import aptech.dating.repository.AdminRepository;

@Service
public class AdminService implements UserDetailsService {

	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final AdminRepository adminRepository;
    
 
	// Use constructor-based dependency injection
    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }
    
    public AdminDTO getAdmin(int id) { 
        Admin admin = this.adminRepository.findById(id).get(); 
        AdminDTO adminDto = this.modelMapper.map(admin, AdminDTO.class); 
        return adminDto; 
    } 
    
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Admin admin = adminRepository.findByUsername(username)
          .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

      return AdminDetailsImpl.build(admin);
    }
}