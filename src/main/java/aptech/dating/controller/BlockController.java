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

import aptech.dating.DTO.BlockDTO;
import aptech.dating.DTO.FamilyDTO;
import aptech.dating.model.Admin;
import aptech.dating.model.Banned;
import aptech.dating.model.Block;
import aptech.dating.model.Family;
import aptech.dating.service.BlockService;

@RestController
@RequestMapping("/api/block")
@CrossOrigin(origins = "http://localhost:4200")
public class BlockController {
	@Autowired
	private ModelMapper modelMapper;
	// Declare the service as final to ensure its immutability
	private final BlockService blockService;

	// Use constructor-based dependency injection
	@Autowired
	public BlockController(BlockService blockService) {
		this.blockService = blockService;
	}

	@GetMapping
	public ResponseEntity<List<BlockDTO>> getAllBlocks() {
		List<Block> block = blockService.getAllBlocks();

		List<BlockDTO> blockDTO = block.stream().map(element -> modelMapper.map(element, BlockDTO.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(blockDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BlockDTO> getBlockById(@PathVariable int id) {
		Optional<Block> block = blockService.getBlockById(id);

		BlockDTO blockDTO = modelMapper.map(block, BlockDTO.class);
		
		return blockDTO!=null?ResponseEntity.ok(blockDTO):ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Block> createBlock(@RequestBody @Validated BlockDTO blockDTO) {
		Block block = modelMapper.map(blockDTO, Block.class);
		return ResponseEntity.ok(blockService.saveBlock(block));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Block> updateBlock(@PathVariable int id, @RequestBody @Validated BlockDTO blockDTO) {
		Optional<Block> block = blockService.getBlockById(id);

	    if (block.isPresent()) {
	    	Block updateBlock = block.get();

	        // Update the existingAdmin with the data from adminDTO
	        modelMapper.map(blockDTO, updateBlock);

	        // Save the updated admin
	        return ResponseEntity.ok(blockService.saveBlock(updateBlock));
	    } else {
	        // If the admin with the given ID is not found, return not found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBlock(@PathVariable int id) {
		blockService.deleteBlock(id);
		return ResponseEntity.ok().build();
	}
}
