package com.stc.filesSystem.service.impl;

import com.stc.filesSystem.exception.ObjectNotFoundException;
import com.stc.filesSystem.model.File;
import com.stc.filesSystem.repository.FileRepository;
import com.stc.filesSystem.service.interfaces.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

	@Autowired
	FileRepository fileRepository;

	@Override
	public File getFileById(Long fileId) {
		return fileRepository.findById(fileId).orElseThrow(() -> {
			throw new ObjectNotFoundException(String.format("No File Exist with id [{}]", fileId));
		});
	}

	@Override
	public List<File> getAllFiles() {
		return fileRepository.findAll();
	}

	@Override
	public File saveFile(File File) {
		try {
			System.out.println(File.getBinary());
			fileRepository.saveAndFlush(File);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return File;
	}

	@Override
	public File updateFile(File newFile) {
		try {
			File oldFile = getFileById(newFile.getId());
			if (oldFile != null) {
				fileRepository.saveAndFlush(newFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return newFile;
	}

	@Override
	public void deleteFile(Long FileId) {
		fileRepository.deleteById(FileId);
	}

	/*
	 * @Override public String getBinaryData(MultipartFile file) throws IOException
	 * { byte [] byteArr=file.getBytes(); String binData = new String(byteArr,
	 * Charset.forName("UTF-8"));
	 * 
	 * return binData; }
	 */

	@Override
	public byte[] getBinaryData(MultipartFile file) throws IOException {
		byte[] byteArr = file.getBytes();

		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(byteArr);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(byteArr.length);
		byte[] tmp = new byte[byteArr.length];
		while (!deflater.finished()) {
			int size = deflater.deflate(tmp);
			outputStream.write(tmp, 0, size);
		}
		try {
			outputStream.close();
		} catch (Exception e) {
		}

		return outputStream.toByteArray();
	}

	@Override
	public byte[] decompressFile(byte[] data) {
		Inflater i = new Inflater();
		i.setInput(data);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4*1024];
		try {
			while (!i.finished()) {
				int count = i.inflate(tmp);
				outputStream.write(tmp, 0, count);
			}
			outputStream.close();
		} catch (Exception exception) {
		}
		return outputStream.toByteArray();
	}


}