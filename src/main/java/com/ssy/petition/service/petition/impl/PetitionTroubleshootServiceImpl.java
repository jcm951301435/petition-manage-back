package com.ssy.petition.service.petition.impl;

import com.github.pagehelper.PageHelper;
import com.ssy.petition.dao.petition.PetitionTroubleshootMapper;
import com.ssy.petition.dto.petition.params.PetitionTroubleshootParams;
import com.ssy.petition.dto.petition.result.PetitionTroubleshootResult;
import com.ssy.petition.entity.petition.PetitionTroubleshoot;
import com.ssy.petition.service.petition.PetitionCompanyService;
import com.ssy.petition.service.petition.PetitionTroubleshootService;
import com.ssy.petition.util.EntityUtils;
import com.ssy.petition.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetitionTroubleshootServiceImpl implements PetitionTroubleshootService {

    private final PetitionTroubleshootMapper mapper;

    private final PetitionCompanyService companyService;

    public PetitionTroubleshootServiceImpl(PetitionTroubleshootMapper mapper, PetitionCompanyService companyService) {
        this.mapper = mapper;
        this.companyService = companyService;
    }

    @Override
    public List<PetitionTroubleshootResult> list(PetitionTroubleshootParams params, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        params.setCompanyId(SecurityUtil.getCheckedCurrentCompanyId());
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

    @Override
    public int insertResultList(List<PetitionTroubleshootResult> resultList) {
        List<PetitionTroubleshoot> list = new ArrayList<>();
        for (PetitionTroubleshootResult result : resultList) {
            PetitionTroubleshoot troubleshoot = transFromPetitionTroubleshootResult(result);
            list.add(troubleshoot);
        }
        return insertList(list);
    }

    @Override
    public int insertList(List<PetitionTroubleshoot> list) {
        List<PetitionTroubleshoot> initList = new ArrayList<>();
        for (PetitionTroubleshoot troubleshoot : list) {
            EntityUtils.initInsertEntity(troubleshoot);
            initList.add(troubleshoot);
        }
        return mapper.insertList(list);
    }

    @Override
    public PetitionTroubleshoot transFromPetitionTroubleshootResult(PetitionTroubleshootResult result) {
        String companyName = result.getCompanyName();
        String teamPetitionStateStr = result.getTeamPetitionStateStr();
        result.setCompanyId(companyService.getCompanyIdByName(companyName));
        if (result.getTeamPetitionState() == null) {
            /* 通过反射直接设置值不会走 set 方法。故此处再执行一次 */
            result.setTeamPetitionStateStr(result.getTeamPetitionStateStr());
            if (result.getTeamPetitionState() == null) {
                throw new RuntimeException("无法分辨值：" + teamPetitionStateStr + "，请录入：群体或单体");
            }
        }
        return result;
    }
}
