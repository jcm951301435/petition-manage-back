package com.ssy.petition.service.petition.impl;

import com.github.pagehelper.PageHelper;
import com.ssy.petition.dao.petition.PetitionContradictionContentMapper;
import com.ssy.petition.dao.petition.PetitionContradictionMapper;
import com.ssy.petition.dao.petition.PetitionContradictionResolveProcessMapper;
import com.ssy.petition.dao.sys.SysFileMapper;
import com.ssy.petition.dto.petition.params.PetitionContradictionEditParams;
import com.ssy.petition.dto.petition.params.PetitionContradictionParams;
import com.ssy.petition.dto.petition.result.PetitionContradictionResult;
import com.ssy.petition.entity.petition.PetitionContradiction;
import com.ssy.petition.entity.petition.PetitionContradictionContent;
import com.ssy.petition.entity.petition.PetitionContradictionResolveProcess;
import com.ssy.petition.entity.sys.SysFile;
import com.ssy.petition.service.petition.PetitionContradictionService;
import com.ssy.petition.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetitionContradictionServiceImpl implements PetitionContradictionService {

    private final PetitionContradictionMapper mapper;

    private final PetitionContradictionContentMapper contradictionContentMapper;

    private final PetitionContradictionResolveProcessMapper resolveProcessMapper;

    private final SysFileMapper sysFileMapper;

    @Autowired
    public PetitionContradictionServiceImpl(PetitionContradictionMapper mapper,
                                            PetitionContradictionContentMapper contradictionContentMapper,
                                            PetitionContradictionResolveProcessMapper resolveProcessMapper,
                                            SysFileMapper sysFileMapper) {
        this.mapper = mapper;
        this.contradictionContentMapper = contradictionContentMapper;
        this.resolveProcessMapper = resolveProcessMapper;
        this.sysFileMapper = sysFileMapper;
    }

    @Override
    public List<PetitionContradictionResult> list(PetitionContradictionParams params, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<PetitionContradictionResult> list = mapper.getList(params);
        List<PetitionContradictionResult> resultList = new ArrayList<>();
        for (PetitionContradictionResult result : list) {
            PetitionContradictionResult newResult = result.toResult();
            resultList.add(newResult);
            Long contradictionId = newResult.getId();
            List<PetitionContradictionContent> contradictionContent = contradictionContentMapper.getListByContradictionId(contradictionId);
            List<PetitionContradictionResolveProcess> contradictionResolveProcess = resolveProcessMapper.getListByContradictionId(contradictionId);
            List<SysFile> sysFiles = sysFileMapper.getListByContradictionId(contradictionId);
            newResult.setContradictionContent(contradictionContent);
            newResult.setContradictionResolveProcess(contradictionResolveProcess);
            newResult.setFileList(sysFiles);
        }
        return resultList;
    }

    @Override
    public Long create(PetitionContradictionEditParams params) {
        PetitionContradiction petitionContradiction = params.toParams();
        EntityUtils.initInsertEntity(petitionContradiction);
        int insertFlag = mapper.insertSelective(petitionContradiction);
        if (insertFlag == 1) {
            for (PetitionContradictionContent temp : params.getContradictionContent()) {
                EntityUtils.initInsertEntity(temp);
                temp.setContradictionId(petitionContradiction.getId());
                insertFlag = contradictionContentMapper.insertSelective(temp);
            }
        }
        if (insertFlag == 1) {
            for (PetitionContradictionResolveProcess temp : params.getContradictionResolveProcess()) {
                EntityUtils.initInsertEntity(temp);
                temp.setContradictionId(petitionContradiction.getId());
                insertFlag = resolveProcessMapper.insertSelective(temp);
            }
        }
        if (insertFlag == 1) {
            return petitionContradiction.getId();
        }
        return null;
    }

    @Override
    public int update(PetitionContradictionEditParams params) {
        PetitionContradiction petitionContradiction = params.toParams();
        EntityUtils.initUpdateEntity(petitionContradiction);
        int insertFlag = mapper.updateByPrimaryKeySelective(petitionContradiction);
        if (insertFlag == 1) {
            for (PetitionContradictionContent temp : params.getContradictionContent()) {
                if (temp.getId() == null) {
                    EntityUtils.initInsertEntity(temp);
                    temp.setContradictionId(petitionContradiction.getId());
                    insertFlag = contradictionContentMapper.insertSelective(temp);
                } else {
                    EntityUtils.initUpdateEntity(temp);
                    temp.setContradictionId(petitionContradiction.getId());
                    insertFlag = contradictionContentMapper.updateByPrimaryKeySelective(temp);
                }
            }
        }
        if (insertFlag == 1) {
            for (PetitionContradictionResolveProcess temp : params.getContradictionResolveProcess()) {
                if (temp.getId() == null) {
                    EntityUtils.initInsertEntity(temp);
                    temp.setContradictionId(petitionContradiction.getId());
                    insertFlag = resolveProcessMapper.insertSelective(temp);
                } else {
                    EntityUtils.initUpdateEntity(temp);
                    temp.setContradictionId(petitionContradiction.getId());
                    insertFlag = resolveProcessMapper.updateByPrimaryKeySelective(temp);
                }
            }
        }
        return insertFlag;
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int disable(Long id) {
        PetitionContradiction contradiction = new PetitionContradiction();
        contradiction.setId(id);
        EntityUtils.initDeleteEntity(contradiction);
        return mapper.updateByPrimaryKeySelective(contradiction);
    }

}
