package com.ssy.petition.service.petition.impl;

import com.github.pagehelper.PageHelper;
import com.ssy.petition.dao.petition.PetitionContradictionMapper;
import com.ssy.petition.dto.petition.params.PetitionContradictionEditParams;
import com.ssy.petition.dto.petition.params.PetitionContradictionParams;
import com.ssy.petition.dto.petition.result.PetitionContradictionResult;
import com.ssy.petition.entity.petition.PetitionContradiction;
import com.ssy.petition.service.petition.PetitionContradictionService;
import com.ssy.petition.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetitionContradictionServiceImpl implements PetitionContradictionService {

    private final PetitionContradictionMapper mapper;

    public PetitionContradictionServiceImpl(PetitionContradictionMapper mapper) {
        this.mapper = mapper;
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
        }
        return resultList;
    }

    @Override
    public int create(PetitionContradictionEditParams params) {
        PetitionContradiction petitionContradiction = params.toParams();
        EntityUtils.initInsertEntity(petitionContradiction);
        return mapper.insertSelective(petitionContradiction);
    }

    @Override
    public int update(PetitionContradictionEditParams params) {
        PetitionContradiction petitionContradiction = params.toParams();
        EntityUtils.initUpdateEntity(petitionContradiction);
        return mapper.updateByPrimaryKeySelective(petitionContradiction);
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
