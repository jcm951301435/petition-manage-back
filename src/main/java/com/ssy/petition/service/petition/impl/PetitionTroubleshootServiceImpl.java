package com.ssy.petition.service.petition.impl;

import com.github.pagehelper.PageHelper;
import com.ssy.petition.dao.petition.PetitionTroubleshootMapper;
import com.ssy.petition.dto.petition.params.PetitionTroubleshootParams;
import com.ssy.petition.dto.petition.result.PetitionTroubleshootResult;
import com.ssy.petition.entity.petition.PetitionTroubleshoot;
import com.ssy.petition.service.petition.PetitionTroubleshootService;
import com.ssy.petition.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetitionTroubleshootServiceImpl implements PetitionTroubleshootService {

    private final PetitionTroubleshootMapper mapper;

    public PetitionTroubleshootServiceImpl(PetitionTroubleshootMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<PetitionTroubleshootResult> list(PetitionTroubleshootParams params, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        return mapper.getList(params);
    }

    @Override
    public int create(PetitionTroubleshoot params) {
        EntityUtils.initInsertEntity(params);
        return mapper.insertSelective(params);
    }

    @Override
    public int update(PetitionTroubleshoot params) {
        EntityUtils.initUpdateEntity(params);
        return mapper.updateByPrimaryKeySelective(params);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int disable(Long id) {
        PetitionTroubleshoot petitionTroubleshoot = new PetitionTroubleshoot();
        petitionTroubleshoot.setId(id);
        EntityUtils.initDeleteEntity(petitionTroubleshoot);
        return mapper.updateByPrimaryKeySelective(petitionTroubleshoot);
    }
}
