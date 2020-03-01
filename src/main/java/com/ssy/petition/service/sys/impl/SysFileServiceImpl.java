package com.ssy.petition.service.sys.impl;

import com.ssy.petition.dao.sys.SysFileMapper;
import com.ssy.petition.entity.sys.SysFile;
import com.ssy.petition.service.sys.SysFileService;
import com.ssy.petition.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SysFileServiceImpl implements SysFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysFileServiceImpl.class);

    private final SysFileMapper mapper;

    @Autowired
    public SysFileServiceImpl(SysFileMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<SysFile> list(Long contradictionId) {
        return mapper.getListByContradictionId(contradictionId);
    }

    @Override
    public SysFile getById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int create(SysFile file) {
        EntityUtils.initInsertEntity(file);
        return mapper.insertSelective(file);
    }

    @Override
    public int update(SysFile file) {
        EntityUtils.initUpdateEntity(file);
        return mapper.updateByPrimaryKeySelective(file);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int disable(Long id) {
        return 0;
    }

    @Override
    public Boolean upload(String filePath, String fileName, MultipartFile multipartFile) {
        boolean result = false;
        String realPath = filePath + fileName;
        File dist = new File(realPath);
        if (!dist.getParentFile().exists()) {
            if (dist.getParentFile().mkdirs()) {
                LOGGER.error("创建文件夹失败{}", realPath);
                result = false;
            }
        }
        try {
            multipartFile.transferTo(dist);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public File getFileById(Long id, String fileBasePath) {
        SysFile sysFile = getById(id);
        if (sysFile == null) {
            return null;
        }
        String fileName = sysFile.getFileName();
        String filePath = fileBasePath + fileName;
        return new File(filePath);
    }
}
