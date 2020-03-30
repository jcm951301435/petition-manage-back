package com.ssy.petition.service.petition.impl;

import com.github.pagehelper.PageHelper;
import com.ssy.petition.common.CommonPage;
import com.ssy.petition.dao.petition.PetitionContradictionContentMapper;
import com.ssy.petition.dao.petition.PetitionContradictionMapper;
import com.ssy.petition.dao.petition.PetitionContradictionResolveProcessMapper;
import com.ssy.petition.dao.petition.PetitionContradictionResolveReasonMapper;
import com.ssy.petition.dao.sys.SysFileMapper;
import com.ssy.petition.dto.petition.params.PetitionContradictionEditParams;
import com.ssy.petition.dto.petition.params.PetitionContradictionParams;
import com.ssy.petition.dto.petition.result.PetitionContradictionResult;
import com.ssy.petition.entity.petition.PetitionContradiction;
import com.ssy.petition.entity.petition.PetitionContradictionContent;
import com.ssy.petition.entity.petition.PetitionContradictionResolveProcess;
import com.ssy.petition.entity.petition.PetitionContradictionResolveReason;
import com.ssy.petition.entity.sys.SysFile;
import com.ssy.petition.service.petition.PetitionCompanyService;
import com.ssy.petition.service.petition.PetitionContradictionService;
import com.ssy.petition.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PetitionContradictionServiceImpl implements PetitionContradictionService {

    private final PetitionContradictionMapper mapper;

    private final PetitionContradictionContentMapper contradictionContentMapper;

    private final PetitionContradictionResolveProcessMapper resolveProcessMapper;

    private final PetitionContradictionResolveReasonMapper resolveReasonMapper;

    private final SysFileMapper sysFileMapper;

    private final PetitionCompanyService companyService;

    @Autowired
    public PetitionContradictionServiceImpl(PetitionContradictionMapper mapper,
                                            PetitionContradictionContentMapper contradictionContentMapper,
                                            PetitionContradictionResolveProcessMapper resolveProcessMapper,
                                            PetitionContradictionResolveReasonMapper resolveReasonMapper,
                                            SysFileMapper sysFileMapper,
                                            PetitionCompanyService companyService) {
        this.mapper = mapper;
        this.contradictionContentMapper = contradictionContentMapper;
        this.resolveProcessMapper = resolveProcessMapper;
        this.resolveReasonMapper = resolveReasonMapper;
        this.sysFileMapper = sysFileMapper;
        this.companyService = companyService;
    }

    @Override
    public List<PetitionContradictionResult> list(PetitionContradictionParams params, Integer pageNum, Integer pageSize) {
        CommonPage<PetitionContradictionResult> page = page(params, pageNum, pageSize);
        return page.getList();
    }

    @Override
    public CommonPage<PetitionContradictionResult> page(PetitionContradictionParams params, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Long> responsibleCompany = new ArrayList<>();
        Long companyId = SecurityUtil.getCheckedCurrentCompanyId();
        if (companyId != null) {
            responsibleCompany.add(SecurityUtil.getCheckedCurrentCompanyId());
        }
        params.setResponsibleCompany(responsibleCompany);
        List<PetitionContradictionResult> list = mapper.getList(params);
        CommonPage<PetitionContradictionResult> page = CommonPage.restPage(list);
        List<PetitionContradictionResult> resultList = new ArrayList<>();
        for (PetitionContradictionResult result : list) {
            PetitionContradictionResult newResult = result.toResult();
            resultList.add(newResult);
            Long contradictionId = newResult.getId();
            List<PetitionContradictionContent> contradictionContent = contradictionContentMapper.getListByContradictionId(contradictionId);
            List<PetitionContradictionResolveProcess> contradictionResolveProcess = resolveProcessMapper.getListByContradictionId(contradictionId);
            List<PetitionContradictionResolveReason> contradictionResolveReason = resolveReasonMapper.getListByContradictionId(contradictionId);
            List<SysFile> sysFiles = sysFileMapper.getListByContradictionId(contradictionId);
            newResult.setContradictionContent(contradictionContent);
            newResult.setContradictionResolveProcess(contradictionResolveProcess);
            newResult.setContradictionResolveReason(contradictionResolveReason);
            newResult.setFileList(sysFiles);
        }
        page.setList(resultList);
        return page;
    }

    @Override
    public List<String> applyNameList(String applyName) {
        return mapper.getApplyNameList(applyName);
    }

    @Override
    @Transactional
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
            for (PetitionContradictionResolveReason temp : params.getContradictionResolveReason()) {
                EntityUtils.initInsertEntity(temp);
                temp.setContradictionId(petitionContradiction.getId());
                insertFlag = resolveReasonMapper.insertSelective(temp);
            }
        }
        if (insertFlag == 1) {
            return petitionContradiction.getId();
        }
        return null;
    }

    @Override
    @Transactional
    public int update(PetitionContradictionEditParams params) {
        PetitionContradiction petitionContradiction = params.toParams();
        EntityUtils.initUpdateEntity(petitionContradiction);
        int insertFlag = mapper.updateByPrimaryKeySelective(petitionContradiction);
         if (insertFlag == 1) {
            List<PetitionContradictionContent> contradictionContents = params.getContradictionContent();
            contradictionContentMapper.deleteByContradictionIdAndNotExists(petitionContradiction.getId(), contradictionContents);
            for (PetitionContradictionContent temp : contradictionContents) {
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
            List<PetitionContradictionResolveProcess> resolveProcesses = params.getContradictionResolveProcess();
            resolveProcessMapper.deleteByContradictionIdAndNotExists(petitionContradiction.getId(), resolveProcesses);
            for (PetitionContradictionResolveProcess temp : resolveProcesses) {
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
        if (insertFlag == 1) {
            List<PetitionContradictionResolveReason> resolveReasons = params.getContradictionResolveReason();
            resolveReasonMapper.deleteByContradictionIdAndNotExists(petitionContradiction.getId(), resolveReasons);
            for (PetitionContradictionResolveReason temp : resolveReasons) {
                if (temp.getId() == null) {
                    EntityUtils.initInsertEntity(temp);
                    temp.setContradictionId(petitionContradiction.getId());
                    insertFlag = resolveReasonMapper.insertSelective(temp);
                } else {
                    EntityUtils.initUpdateEntity(temp);
                    temp.setContradictionId(petitionContradiction.getId());
                    insertFlag = resolveReasonMapper.updateByPrimaryKeySelective(temp);
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

    @Override
    public PetitionContradiction transFromPetitionContradictionResult(PetitionContradictionResult result) {
        result.setApplySex(getSexValue(result.getApplySexStr()));
        result.setApplyBirth(getDateValue(result.getApplyBirthStr(), "yyyy-MM"));
        result.setPetitionType("[" + result.getPetitionTypeStr() + "]");
        result.setCheckType(getBooleanValue(result.getCheckTypeStr()));
        String companyName = result.getCompanyName();
        result.setResponsibleCompany(companyService.getCompanyIdByName(companyName));
        result.setTeamPetitionState(getBooleanValue(result.getTeamPetitionStateStr()));
        result.setFirstPetitionTime(getDateValue(result.getFirstPetitionTimeStr(), "yyyy-MM-dd"));
        result.setLastPetitionTime(getDateValue(result.getLastPetitionTimeStr(), "yyyy-MM-dd"));
        result.setLawsuit(getBooleanValue(result.getLawsuitStr()));
        return result;
    }

    private List<PetitionContradictionContent> getContentListFromContradiction(Long contradictionId, PetitionContradictionResult result) {
        List<PetitionContradictionContent> contentList = new ArrayList<>();
        String contradictionContentFirstStr = result.getContradictionContentFirstStr();
        if (StringUtils.isNotEmpty(contradictionContentFirstStr)) {
            PetitionContradictionContent contradictionContent = new PetitionContradictionContent();
            contradictionContent.setContradictionId(contradictionId);
            contradictionContent.setContradictionContent(contradictionContentFirstStr);
            EntityUtils.initInsertEntity(contradictionContent);
            contentList.add(contradictionContent);
        }
        return contentList;
    }

    private List<PetitionContradictionResolveProcess> getProcessListFromContradiction(Long contradictionId, PetitionContradictionResult result) {
        List<PetitionContradictionResolveProcess> processList = new ArrayList<>();
        String contradictionResolveProcessFirstStr = result.getContradictionResolveProcessFirstStr();
        if (StringUtils.isNotEmpty(contradictionResolveProcessFirstStr)) {
            PetitionContradictionResolveProcess process = new PetitionContradictionResolveProcess();
            process.setContradictionId(contradictionId);
            process.setResolveContent(contradictionResolveProcessFirstStr);
            EntityUtils.initInsertEntity(process);
            processList.add(process);
        }
        return processList;
    }

    private List<PetitionContradictionResolveReason> getReasonListFromContradiction(Long contradictionId, PetitionContradictionResult result) {
        List<PetitionContradictionResolveReason> reasonList = new ArrayList<>();
        String contradictionResolveReasonFirstStr = result.getContradictionResolveReasonFirstStr();
        if (StringUtils.isNotEmpty(contradictionResolveReasonFirstStr)) {
            PetitionContradictionResolveReason reason = new PetitionContradictionResolveReason();
            reason.setContradictionId(contradictionId);
            reason.setReason(contradictionResolveReasonFirstStr);
            EntityUtils.initInsertEntity(reason);
            reasonList.add(reason);
        }
        return reasonList;
    }

    @Override
    @Transactional
    public int insertResultList(List<PetitionContradictionResult> resultList) {
        List<PetitionContradiction> list = new ArrayList<>();
        List<PetitionContradictionContent> contentList = new ArrayList<>();
        List<PetitionContradictionResolveProcess> processList = new ArrayList<>();
        List<PetitionContradictionResolveReason> reasonList = new ArrayList<>();
        for (PetitionContradictionResult result : resultList) {
            PetitionContradiction contradiction = transFromPetitionContradictionResult(result);
            EntityUtils.initInsertEntity(contradiction);
            list.add(contradiction);
            contentList.addAll(getContentListFromContradiction(contradiction.getId(), result));
            processList.addAll(getProcessListFromContradiction(contradiction.getId(), result));
            reasonList.addAll(getReasonListFromContradiction(contradiction.getId(), result));
        }
        int flag = insertList(list);
        if (CollectionUtils.isNotEmpty(contentList)) {
            contradictionContentMapper.insertList(contentList);
        }
        if (CollectionUtils.isNotEmpty(processList)) {
            resolveProcessMapper.insertList(processList);
        }
        if (CollectionUtils.isNotEmpty(reasonList)) {
            resolveReasonMapper.insertList(reasonList);
        }
        return flag;
    }

    @Override
    @Transactional
    public int insertList(List<PetitionContradiction> list) {
        return mapper.insertList(list);
    }

    private String getSexValue(String str) {
        if (StringUtils.isNotEmpty(str)) {
            if (str.equals("男")) {
                return "1";
            }
            if (str.equals("女")) {
                return "0";
            }
            throw new RuntimeException("无法识别值：" + str + "，请输入 男/女");
        }
        return null;
    }

    private Date getDateValue(String dateStr, String format) {
        Date result;
        try {
            result = DateUtils.parseDate(dateStr, format);
        } catch (Exception e) {
            throw new RuntimeException("日期格式错误，请输入如下格式：" + format);
        }
        return result;
    }

    private Boolean getBooleanValue(String bool) {
        if (StringUtils.isEmpty(bool)) {
            return null;
        }
        if (bool.equals("是")) {
            return true;
        }
        if (bool.equals("否")) {
            return false;
        }
        throw new RuntimeException("无法识别值：" + bool + "，请输入 是/否");
    }

}
