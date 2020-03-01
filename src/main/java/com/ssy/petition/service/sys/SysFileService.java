package com.ssy.petition.service.sys;

import com.ssy.petition.entity.sys.SysFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface SysFileService {

    List<SysFile> list(Long contradictionId);

    SysFile getById(Long id);

    int create(SysFile file);

    int update(SysFile file);

    int delete(Long id);

    int disable(Long id);

    Boolean upload(String filePath, String fileName, MultipartFile multipartFile);

    File getFileById(Long id, String fileBasePath);

}
