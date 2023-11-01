package com.stc.filesSystem.controller;

import com.stc.filesSystem.dto.ItemDto;
import com.stc.filesSystem.enums.ItemType;
import com.stc.filesSystem.model.File;
import com.stc.filesSystem.model.Item;
import com.stc.filesSystem.service.interfaces.FileService;
import com.stc.filesSystem.service.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/files")
public class FilesSystemController {

	@Autowired
	ItemService itemService;


	@PostMapping("SPACE")
	public ResponseEntity<Item> saveSpace(@RequestBody ItemDto itemDto) throws Exception {

		itemDto.setType(ItemType.SPACE);
		return new ResponseEntity<>(itemService.saveItem(itemDto), HttpStatus.CREATED);
	}

	@PostMapping("FOLDER")
	public ResponseEntity<Item> saveFolder(@RequestBody ItemDto itemDto) throws Exception {

		itemDto.setType(ItemType.FOLDER);
		return new ResponseEntity<>(itemService.saveItem(itemDto), HttpStatus.CREATED);
	}

	@PostMapping("/FILE/{parentId}")
	@Transactional
	public ResponseEntity<Item> saveFile(@PathVariable("parentId") Long parentId,
			@RequestParam("file") MultipartFile file, @RequestHeader("userMail") String userMail) throws Exception {

		ItemDto itemDto = new ItemDto(ItemType.FILE, file.getOriginalFilename(), userMail, parentId, null, file, null);
		return new ResponseEntity<>(itemService.saveItem(itemDto), HttpStatus.CREATED);

	}

	@GetMapping("/FILE/{parentId}/{fileId}")
	@Transactional
	public ResponseEntity<?> getImage(@PathVariable("parentId") Long parentId, @PathVariable("fileId") Long fileId, @RequestHeader("userMail") String userMail)
			throws Exception {

		ItemDto itemDto = new ItemDto(null, null, userMail, parentId, null, null, fileId);
		
		File file = itemService.downloadFileById(itemDto);
		//IMAGE_PNG
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(file.getFiletype()))
				.body(file.getBinary());

	}

}
