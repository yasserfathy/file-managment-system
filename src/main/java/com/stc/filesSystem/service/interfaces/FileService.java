package com.stc.filesSystem.service.interfaces;


import com.stc.filesSystem.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {


    File getFileById(Long FileId) throws Exception;

    List<File> getAllFiles();

    File saveFile(File File);

    File updateFile(File File);

    void deleteFile(Long FileId);

    byte[] getBinaryData(MultipartFile file) throws IOException;
    
    byte[] decompressFile(byte[] data);
    


}
