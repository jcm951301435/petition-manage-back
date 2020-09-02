package com.ssy.petition.service.petition.impl;

import com.github.pagehelper.PageHelper;
import com.ssy.petition.dao.petition.PetitionCompanyMapper;
import com.ssy.petition.dto.petition.params.PetitionCompanyParams;
import com.ssy.petition.dto.petition.result.PetitionCompanyResult;
import com.ssy.petition.entity.base.example.BaseExample;
import com.ssy.petition.entity.petition.PetitionCompany;
import com.ssy.petition.service.petition.PetitionCompanyService;
import com.ssy.petition.util.EntityUtils;
import com.ssy.petition.util.SecurityUtil;
import com.ssy.petition.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetitionCompanyServiceImpl implements PetitionCompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PetitionCompanyServiceImpl.class);

    private final PetitionCompanyMapper mapper;

    public PetitionCompanyServiceImpl(PetitionCompanyMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<PetitionCompanyResult> list(PetitionCompanyParams params, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        params.setId(SecurityUtil.getCheckedCurrentCompanyId());
        return mapper.getList(params);
    }

    @Override
    public int create(PetitionCompany company) {
        EntityUtils.initInsertEntity(company);
        return mapper.insertSelective(company);
    }

    @Override
    public int update(PetitionCompany company) {
        EntityUtils.initUpdateEntity(company);
        return mapper.updateByPrimaryKeySelective(company);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int disable(Long id) {
        PetitionCompany company = new PetitionCompany();
        company.setId(id);
        EntityUtils.initDeleteEntity(company);
        return mapper.updateByPrimaryKeySelective(company);
    }

    @Override
    public List<PetitionCompany> listByName(String companyName) {
        BaseExample example = new BaseExample();
        example.createCriteria().andFieldEqualTo("name", companyName);
        return mapper.selectByExample(example);
    }

    @Override
    public Long getCompanyIdByName(String companyName) {
        if (StringUtils.isNotEmpty(companyName)) {
            List<PetitionCompany> companyList = listByName(companyName);
            if (companyList.size() <= 0) {
                throw new RuntimeException("找不到此公司，请检查是否录入错误。公司名：" + companyName);
            }
            if (companyList.size() > 1) {
                throw new RuntimeException("此公司名存在重复，无法确定唯一。公司名：" + companyName);
            }
            return companyList.get(0).getId();
        }
        return null;
    }

    @Override
    public List<PetitionCompanyResult> listAll() {
        PetitionCompanyParams params = new PetitionCompanyParams();
        return mapper.getList(params);
    }
}
