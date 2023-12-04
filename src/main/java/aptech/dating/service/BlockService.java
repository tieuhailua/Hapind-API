package aptech.dating.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aptech.dating.DTO.AdminDTO;
import aptech.dating.DTO.BlockDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Block;
import aptech.dating.repository.BlockRepository;

@Service
public class BlockService {
	@Autowired
    private ModelMapper modelMapper;
    // Declare the repository as final to ensure its immutability
    private final BlockRepository blockRepository;

    // Use constructor-based dependency injection
    @Autowired
    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    public List<Block> getAllBlocks() {
        return blockRepository.findAll();
    }

    public Optional<Block> getBlockById(int id) {
        return blockRepository.findById(id);
    }

    public Block saveBlock(Block block) {
        return blockRepository.save(block);
    }

    public void deleteBlock(int id) {
    	blockRepository.deleteById(id);
    }
    
    public BlockDTO getBlock(int id) { 
        Block block = this.blockRepository.findById(id).get(); 
        BlockDTO blockDto = this.modelMapper.map(block, BlockDTO.class); 
        return blockDto; 
    } 
}

