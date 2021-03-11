package com.ssy.petition.provider;

import com.ssy.petition.dto.petition.result.PetitionCompanyResult;
import com.ssy.petition.entity.cal.*;
import com.ssy.petition.service.petition.PetitionCompanyService;
import com.ssy.petition.util.CollectionUtils;
import com.ssy.petition.util.SecurityUtil;
import com.ssy.petition.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: jcm
 * @date: 2020/08/26
 */
@Component
public class CalculateProvider {

    private static PetitionCompanyService companyService;

    public static PetitionCompanyService getCompanyService() {
        return companyService;
    }

    @Autowired
    public void setCompanyService(PetitionCompanyService companyService) {
        CalculateProvider.companyService = companyService;
    }

    /**
     * 不同人使用不同的计算类
     */
    private static final Map<Long, Calculating> CALCULATE_MAP = new HashMap<>();

    /**
     * 根据人员id获取对应计算类
     *
     * @param userId
     * @return
     */
    public static Calculating getCalculating(Long userId) {
        if (CALCULATE_MAP.containsKey(userId)) {
            return CALCULATE_MAP.get(userId);
        }
        Calculating calculating = new Calculating();
        CALCULATE_MAP.put(userId, calculating);
        return calculating;
    }

    /**
     * 根据人员id获取对应计算类
     *
     * @return
     */
    public static Calculating getCalculating() {
        Long userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            throw new RuntimeException("无法获取当前人员信息，请联系管理员！");
        }
        return getCalculating(userId);
    }

    /**
     * 清空
     */
    public static void clearCalculating() {
        Long userId = SecurityUtil.getCurrentUserId();
        if (CALCULATE_MAP.containsKey(userId)) {
            CALCULATE_MAP.put(userId, new Calculating());
        }
    }

    /**
     * 构造实体
     *
     * @param list
     * @param file
     * @param <T>
     * @return
     */
    public static <T extends ParamsEntity> CalculateEntity<T> generateCalculateEntity(List<T> list, MultipartFile file) {
        CalculateEntity<T> calculateEntity = new CalculateEntity<>();
        List<T> filterList = list.stream()
                .filter(t -> StringUtils.isNotEmpty(t.getCompanyName()) && !"总数".equals(t.getCompanyName()))
                .collect(Collectors.toList());
        Map<String, T> map = filterList.stream()
                .collect(Collectors.toMap(T::getCompanyName, t -> t,
                (key1, key2) -> {throw new RuntimeException(String.format("公司名称重复：[%s]", key1.getCompanyName()));}));
        calculateEntity.setList(filterList);
        List<String> companyNameList = filterList.stream()
                .map(ParamsEntity::getCompanyName).collect(Collectors.toList());
        calculateEntity.setMap(map);
        calculateEntity.setFile(file);
        calculateEntity.setCompanyNameList(companyNameList);
        return calculateEntity;
    }

    /**
     * 导入
     *
     * @param list
     * @param file
     * @param calculatedTypeEnum
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public static <T extends ParamsEntity> void addCalculating(List<T> list, MultipartFile file, CalculatedTypeEnum calculatedTypeEnum) {
        Calculating calculating = getCalculating();
        if (list == null) {
            list = new ArrayList<>();
        }
        switch (calculatedTypeEnum) {
            case ADMIN_CYCLE_ACCEPT:
                CalculateEntity<CycleAccept> adminCycleAccept = generateCalculateEntity((List<CycleAccept>) list, file);
                calculating.setAdminCycleAccepts(adminCycleAccept);
                updateCompanyList(calculating, adminCycleAccept);
                break;
            case PARTY_CYCLE_ACCEPT:
                CalculateEntity<CycleAccept> partyCycleAccept = generateCalculateEntity((List<CycleAccept>) list, file);
                calculating.setPartyCycleAccepts(partyCycleAccept);
                updateCompanyList(calculating, partyCycleAccept);
                break;
            case ADMIN_FIRST_ACCEPT:
                CalculateEntity<FirstAccept> adminFirstAccept = generateCalculateEntity((List<FirstAccept>) list, file);
                calculating.setAdminFirstAccepts(adminFirstAccept);
                updateCompanyList(calculating, adminFirstAccept);
                break;
            case PARTY_FIRST_ACCEPT:
                CalculateEntity<FirstAccept> partyFirstAccept = generateCalculateEntity((List<FirstAccept>) list, file);
                calculating.setPartyFirstAccepts(partyFirstAccept);
                updateCompanyList(calculating, partyFirstAccept);
                break;
            case ADMIN_FIRST_CONTACT:
                CalculateEntity<FirstContact> adminFirstContact = generateCalculateEntity((List<FirstContact>) list, file);
                calculating.setAdminFirstContacts(adminFirstContact);
                updateCompanyList(calculating, adminFirstContact);
                break;
            case PARTY_FIRST_CONTACT:
                CalculateEntity<FirstContact> partyFirstContact = generateCalculateEntity((List<FirstContact>) list, file);
                calculating.setPartyFirstContacts(partyFirstContact);
                updateCompanyList(calculating, partyFirstContact);
                break;
            case ADMIN_REPEAT_PETITION:
                CalculateEntity<RepeatPetition> adminRepeatPetition = generateCalculateEntity((List<RepeatPetition>) list, file);
                calculating.setAdminRepeatPetitions(adminRepeatPetition);
                updateCompanyList(calculating, adminRepeatPetition);
                break;
            case PARTY_REPEAT_PETITION:
                CalculateEntity<RepeatPetition> partyRepeatPetition = generateCalculateEntity((List<RepeatPetition>) list, file);
                calculating.setPartyRepeatPetitions(partyRepeatPetition);
                updateCompanyList(calculating, partyRepeatPetition);
                break;
            case ADMIN_PUBLIC_REPLY:
                CalculateEntity<PublicReply> adminPublicReply = generateCalculateEntity((List<PublicReply>) list, file);
                calculating.setAdminPublicReplies(adminPublicReply);
                updateCompanyList(calculating, adminPublicReply);
                break;
            case PARTY_PUBLIC_REPLY:
                CalculateEntity<PublicReply> partyPublicReply = generateCalculateEntity((List<PublicReply>) list, file);
                calculating.setPartyPublicReplies(partyPublicReply);
                updateCompanyList(calculating, partyPublicReply);
                break;
            case ADMIN_SATISFACTION:
                CalculateEntity<Satisfaction> adminSatisfaction = generateCalculateEntity((List<Satisfaction>) list, file);
                calculating.setAdminSatisfactions(adminSatisfaction);
                updateCompanyList(calculating, adminSatisfaction);
                break;
            case PARTY_SATISFACTION:
                CalculateEntity<Satisfaction> partySatisfaction = generateCalculateEntity((List<Satisfaction>) list, file);
                calculating.setPartySatisfactions(partySatisfaction);
                updateCompanyList(calculating, partySatisfaction);
                break;
            case ADMIN_RIGHT:
                CalculateEntity<Satisfaction> adminRight = generateCalculateEntity((List<Satisfaction>) list, file);
                calculating.setAdminRight(adminRight);
                updateCompanyList(calculating, adminRight);
                break;
            case PARTY_RIGHT:
                CalculateEntity<Satisfaction> partyRight = generateCalculateEntity((List<Satisfaction>) list, file);
                calculating.setPartyRight(partyRight);
                updateCompanyList(calculating, partyRight);
                break;
            default:
                break;
        }
    }

    /**
     * 统计公司名单
     */
    public static <T extends ParamsEntity> void updateCompanyList(Calculating calculating, CalculateEntity<T> entity) {
        calculating.setSaveStatus(false);
        if (isAllTypeImported(calculating)) {
            Set<String> companyNameSet = new HashSet<>();
            CalculateEntity<CycleAccept> adminCycleAcceptEntity = calculating.getAdminCycleAccepts();
            CalculateEntity<CycleAccept> partyCycleAcceptEntity = calculating.getPartyCycleAccepts();
            CalculateEntity<FirstAccept> adminFirstAcceptEntity = calculating.getAdminFirstAccepts();
            CalculateEntity<FirstAccept> partyFirstAcceptEntity = calculating.getPartyFirstAccepts();
            CalculateEntity<FirstContact> adminFirstContactEntity = calculating.getAdminFirstContacts();
            CalculateEntity<FirstContact> partyFirstContactEntity = calculating.getPartyFirstContacts();
            CalculateEntity<PublicReply> adminPublicReplyEntity = calculating.getAdminPublicReplies();
            CalculateEntity<PublicReply> partyPublicReplyEntity = calculating.getPartyPublicReplies();
            CalculateEntity<RepeatPetition> adminRepeatPetitionEntity = calculating.getAdminRepeatPetitions();
            CalculateEntity<RepeatPetition> partyRepeatPetitionEntity = calculating.getPartyRepeatPetitions();
            CalculateEntity<Satisfaction> adminSatisfactionEntity = calculating.getAdminSatisfactions();
            CalculateEntity<Satisfaction> partySatisfactionEntity = calculating.getPartySatisfactions();
            CalculateEntity<Satisfaction> adminRightEntity = calculating.getAdminRight();
            CalculateEntity<Satisfaction> partyRightEntity = calculating.getPartyRight();

            if (adminCycleAcceptEntity != null) {
                companyNameSet.addAll(adminCycleAcceptEntity.getCompanyNameList());
            }
            if (partyCycleAcceptEntity != null) {
                companyNameSet.addAll(partyCycleAcceptEntity.getCompanyNameList());
            }
            if (adminFirstAcceptEntity != null) {
                companyNameSet.addAll(adminFirstAcceptEntity.getCompanyNameList());
            }
            if (partyFirstAcceptEntity != null) {
                companyNameSet.addAll(partyFirstAcceptEntity.getCompanyNameList());
            }
            if (adminFirstContactEntity != null) {
                companyNameSet.addAll(adminFirstContactEntity.getCompanyNameList());
            }
            if (partyFirstContactEntity != null) {
                companyNameSet.addAll(partyFirstContactEntity.getCompanyNameList());
            }
            if (adminPublicReplyEntity != null) {
                companyNameSet.addAll(adminPublicReplyEntity.getCompanyNameList());
            }
            if (partyPublicReplyEntity != null) {
                companyNameSet.addAll(partyPublicReplyEntity.getCompanyNameList());
            }
            if (adminRepeatPetitionEntity != null) {
                companyNameSet.addAll(adminRepeatPetitionEntity.getCompanyNameList());
            }
            if (partyRepeatPetitionEntity != null) {
                companyNameSet.addAll(partyRepeatPetitionEntity.getCompanyNameList());
            }
            if (adminSatisfactionEntity != null) {
                companyNameSet.addAll(adminSatisfactionEntity.getCompanyNameList());
            }
            if (partySatisfactionEntity != null) {
                companyNameSet.addAll(partySatisfactionEntity.getCompanyNameList());
            }
            if (adminRightEntity != null) {
                companyNameSet.addAll(adminRightEntity.getCompanyNameList());
            }
            if (partyRightEntity != null) {
                companyNameSet.addAll(partyRightEntity.getCompanyNameList());
            }

            calculating.setCompanyNameSet(companyNameSet);

            List<PetitionCompanyResult> companyResultList = companyService.listAll();
            Map<String, PetitionCompanyResult> companyShortResultMap = new HashMap<>();
            Map<String, PetitionCompanyResult> companyResultMap = new HashMap<>();
            if (CollectionUtils.isNotEmpty(companyResultList)) {
                companyShortResultMap = companyResultList.stream().collect(
                        Collectors.toMap(PetitionCompanyResult::getShortName, company -> company,
                        (key1, key2) -> key2));
                companyResultMap = companyResultList.stream().collect(
                        Collectors.toMap(PetitionCompanyResult::getName, company -> company,
                                (key1, key2) -> key2));
            }
            List<Calculated> calculatedList = new ArrayList<>();
            for (String companyName : companyNameSet) {
                Calculated calculated = new Calculated();
                PetitionCompanyResult companyResult = null;
                if (companyResultMap.containsKey(companyName)) {
                    companyResult = companyResultMap.get(companyName);
                }
                if (companyShortResultMap.containsKey(companyName)) {
                    companyResult = companyShortResultMap.get(companyName);
                }
                if (companyResult == null) {
                    continue;
//                    calculated.setCompanyName(companyName);
//                    calculated.setShortName(companyName);
                } else {
                    String shortName = companyResult.getShortName();
                    String companyNameFound = companyResult.getName();
                    if (StringUtils.isEmpty(shortName)) {
                        shortName = companyNameFound;
                    }
                    calculated.setCompanyName(companyNameFound);
                    calculated.setShortName(shortName);
                }
                Integer sort = null;
                if (companyResult != null) {
                    sort = companyResult.getSort();
                }
                calculated.setSort(sort);
                calculatedList.add(calculated);
            }
            List<Calculated> afterSortList = calculatedList.stream().sorted(Comparator.comparing(Calculated::getSort, (s1, s2) -> {
                if (s1 == null) {
                    if (s2 == null) {
                        return 0;
                    }
                    return 1;
                } else {
                    if (s2 == null) {
                        return -1;
                    }
                    return s1.compareTo(s2);
                }
            })).collect(Collectors.toList());
            calculating.setCalculatedList(afterSortList);
            calculating.setRecordCount(calculatedList.size());
            calculating.setStatus(CalculatedStatusEnum.NO_CALCULATE);
            calculating.setCalculatedCount(0);
        } else {
            calculating.setStatus(CalculatedStatusEnum.NO_IMPORTED);
        }
    }

    /**
     * 是否全部导入
     *
     * @param calculating
     * @return
     */
    public static boolean isAllTypeImported(Calculating calculating) {
        boolean result = true;
        if (calculating.getStatus() == null || CalculatedStatusEnum.NO_IMPORTED.equals(calculating.getStatus())) {
            for (CalculatedTypeEnum calculatedTypeEnum : CalculatedTypeEnum.values()) {
                if (!isTypeImported(calculating, calculatedTypeEnum)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 该类别是否导入
     *
     * @param calculating
     * @param calculatedTypeEnum
     * @return
     */
    public static boolean isTypeImported(Calculating calculating, CalculatedTypeEnum calculatedTypeEnum) {
        Map<String, Object> map = getFileRecordByType(calculating, calculatedTypeEnum);
        Object fileName = map.get("fileName");
        return StringUtils.isNotEmpty(fileName);
    }

    public static Map<String, Object> getFileRecordByType(Calculating calculating, CalculatedTypeEnum calculatedTypeEnum) {
        return getFileRecordByType(calculating, calculatedTypeEnum, false);
    }

    /**
     * 获取对应类别文件名称
     *
     * @param calculating
     * @param calculatedTypeEnum
     * @return
     */
    public static Map<String, Object> getFileRecordByType(Calculating calculating, CalculatedTypeEnum calculatedTypeEnum, boolean hasFile) {
        Map<String, Object> result = new HashMap<>();
        String fileName = "";
        int count = 0;
        MultipartFile file = null;
        switch (calculatedTypeEnum) {
            case ADMIN_CYCLE_ACCEPT:
                CalculateEntity<CycleAccept> adminCycleAccept = calculating.getAdminCycleAccepts();
                if (adminCycleAccept != null && adminCycleAccept.getFile() != null) {
                    file = adminCycleAccept.getFile();
                    count = adminCycleAccept.getList().size();
                }
                break;
            case PARTY_CYCLE_ACCEPT:
                CalculateEntity<CycleAccept> partyCycleAccept = calculating.getPartyCycleAccepts();
                if (partyCycleAccept != null && partyCycleAccept.getFile() != null) {
                    file = partyCycleAccept.getFile();
                    count = partyCycleAccept.getList().size();
                }
                break;
            case ADMIN_FIRST_ACCEPT:
                CalculateEntity<FirstAccept> adminFirstAccept = calculating.getAdminFirstAccepts();
                if (adminFirstAccept != null && adminFirstAccept.getFile() != null) {
                    file = adminFirstAccept.getFile();
                    count = adminFirstAccept.getList().size();
                }
                break;
            case PARTY_FIRST_ACCEPT:
                CalculateEntity<FirstAccept> partyFirstAccept = calculating.getPartyFirstAccepts();
                if (partyFirstAccept != null && partyFirstAccept.getFile() != null) {
                    file = partyFirstAccept.getFile();
                    count = partyFirstAccept.getList().size();
                }
                break;
            case ADMIN_FIRST_CONTACT:
                CalculateEntity<FirstContact> adminFirstContact = calculating.getAdminFirstContacts();
                if (adminFirstContact != null && adminFirstContact.getFile() != null) {
                    file = adminFirstContact.getFile();
                    count = adminFirstContact.getList().size();
                }
                break;
            case PARTY_FIRST_CONTACT:
                CalculateEntity<FirstContact> partyFirstContact = calculating.getPartyFirstContacts();
                if (partyFirstContact != null && partyFirstContact.getFile() != null) {
                    file = partyFirstContact.getFile();
                    count = partyFirstContact.getList().size();
                }
                break;
            case ADMIN_REPEAT_PETITION:
                CalculateEntity<RepeatPetition> adminRepeatPetition = calculating.getAdminRepeatPetitions();
                if (adminRepeatPetition != null && adminRepeatPetition.getFile() != null) {
                    file = adminRepeatPetition.getFile();
                    count = adminRepeatPetition.getList().size();
                }
                break;
            case PARTY_REPEAT_PETITION:
                CalculateEntity<RepeatPetition> partyRepeatPetition = calculating.getPartyRepeatPetitions();
                if (partyRepeatPetition != null && partyRepeatPetition.getFile() != null) {
                    file = partyRepeatPetition.getFile();
                    count = partyRepeatPetition.getList().size();
                }
                break;
            case ADMIN_PUBLIC_REPLY:
                CalculateEntity<PublicReply> adminPublicReply = calculating.getAdminPublicReplies();
                if (adminPublicReply != null && adminPublicReply.getFile() != null) {
                    file = adminPublicReply.getFile();
                    count = adminPublicReply.getList().size();
                }
                break;
            case PARTY_PUBLIC_REPLY:
                CalculateEntity<PublicReply> partyPublicReply = calculating.getPartyPublicReplies();
                if (partyPublicReply != null && partyPublicReply.getFile() != null) {
                    file = partyPublicReply.getFile();
                    count = partyPublicReply.getList().size();
                }
                break;
            case ADMIN_SATISFACTION:
                CalculateEntity<Satisfaction> adminSatisfaction = calculating.getAdminSatisfactions();
                if (adminSatisfaction != null && adminSatisfaction.getFile() != null) {
                    file = adminSatisfaction.getFile();
                    count = adminSatisfaction.getList().size();
                }
                break;
            case PARTY_SATISFACTION:
                CalculateEntity<Satisfaction> partySatisfaction = calculating.getPartySatisfactions();
                if (partySatisfaction != null && partySatisfaction.getFile() != null) {
                    file = partySatisfaction.getFile();
                    count = partySatisfaction.getList().size();
                }
                break;
            case ADMIN_RIGHT:
                CalculateEntity<Satisfaction> adminRight = calculating.getAdminRight();
                if (adminRight != null && adminRight.getFile() != null) {
                    file = adminRight.getFile();
                    count = adminRight.getList().size();
                }
                break;
            case PARTY_RIGHT:
                CalculateEntity<Satisfaction> partyRight = calculating.getPartyRight();
                if (partyRight != null && partyRight.getFile() != null) {
                    file = partyRight.getFile();
                    count = partyRight.getList().size();
                }
            default:
                break;
        }
        if (file != null) {
            fileName = file.getOriginalFilename();
        }
        result.put("fileName", fileName);
        result.put("count", count);
        if (hasFile) {
            result.put("file", file);
        }
        return result;
    }

    /**
     * 查询计算信息
     *
     * @return
     */
    public static Map<String, Object> queryCalculate() {
        Map<String, Object> result = new HashMap<>();
        Calculating calculating = getCalculating();
        int recordCount = 0;
        int calculatedCount = 0;
        boolean isAllTypeImported = false;
        boolean isCalculating = false;
        boolean isCalculated = false;
        int progressCount = 0;
        String currentCompanyName = "";
        if (calculating != null) {
            recordCount = isnull(calculating.getRecordCount());
            calculatedCount = isnull(calculating.getCalculatedCount());
            isAllTypeImported = isAllTypeImported(calculating);
            isCalculating = CalculatedStatusEnum.CALCULATING.equals(calculating.getStatus());
            isCalculated = CalculatedStatusEnum.CALCULATED.equals(calculating.getStatus());
            BigDecimal progressCountBig = BigDecimal.ZERO;
            if (recordCount != 0) {
                progressCountBig = divide(calculatedCount, recordCount);
            }
            progressCount = progressCountBig.multiply(new BigDecimal(100)).intValue();
            currentCompanyName = calculating.getCurrentCompanyName();
        }
        result.put("isAllTypeImported", isAllTypeImported);
        result.put("isCalculating", isCalculating);
        result.put("isCalculated", isCalculated);
        result.put("recordCount", recordCount);
        result.put("calculatedCount", calculatedCount);
        result.put("progressCount", progressCount);
        result.put("currentCompanyName", currentCompanyName);
        return result;
    }

    /**
     * 查询导入信息
     * @return
     */
    public static Map<String, Map<String, Object>> queryImport() {
        Map<String, Map<String, Object>> result = new HashMap<>();
        Calculating calculating = getCalculating();
        for (CalculatedTypeEnum typeEnum : CalculatedTypeEnum.values()) {
            Map<String, Object> mapTemp = getFileRecordByType(calculating, typeEnum);
            result.put(typeEnum.getName(), mapTemp);
        }
        return result;
    }


    /**
     * 计算
     *
     * @param calculating
     */
    public static void calculate(Calculating calculating) {
        if (calculating == null) {
            calculating = getCalculating();
        }
        if (isAllTypeImported(calculating)) {
            calculating.setStatus(CalculatedStatusEnum.CALCULATING);
            List<Calculated> calculatedList = calculating.getCalculatedList();
            Integer calculatedCount = 0;
            calculating.setCalculatedCount(calculatedCount);
            Integer recordCount = isnull(calculating.getRecordCount());
            CalculateEntity<CycleAccept> adminCycleAcceptEntity = calculating.getAdminCycleAccepts();
            CalculateEntity<CycleAccept> partyCycleAcceptEntity = calculating.getPartyCycleAccepts();
            CalculateEntity<FirstAccept> adminFirstAcceptEntity = calculating.getAdminFirstAccepts();
            CalculateEntity<FirstAccept> partyFirstAcceptEntity = calculating.getPartyFirstAccepts();
            CalculateEntity<FirstContact> adminFirstContactEntity = calculating.getAdminFirstContacts();
            CalculateEntity<FirstContact> partyFirstContactEntity = calculating.getPartyFirstContacts();
            CalculateEntity<PublicReply> adminPublicReplyEntity = calculating.getAdminPublicReplies();
            CalculateEntity<PublicReply> partyPublicReplyEntity = calculating.getPartyPublicReplies();
            CalculateEntity<RepeatPetition> adminRepeatPetitionEntity = calculating.getAdminRepeatPetitions();
            CalculateEntity<RepeatPetition> partyRepeatPetitionEntity = calculating.getPartyRepeatPetitions();
            CalculateEntity<Satisfaction> adminSatisfactionEntity = calculating.getAdminSatisfactions();
            CalculateEntity<Satisfaction> partySatisfactionEntity = calculating.getPartySatisfactions();
            CalculateEntity<Satisfaction> adminRightEntity = calculating.getAdminRight();
            CalculateEntity<Satisfaction> partyRightEntity = calculating.getPartyRight();

            Map<String, CycleAccept> adminCycleAcceptMap = adminCycleAcceptEntity.getMap();
            Map<String, CycleAccept> partyCycleAcceptMap = partyCycleAcceptEntity.getMap();
            Map<String, FirstAccept> adminFirstAcceptMap = adminFirstAcceptEntity.getMap();
            Map<String, FirstAccept> partyFirstAcceptMap = partyFirstAcceptEntity.getMap();
            Map<String, FirstContact> adminFirstContactMap = adminFirstContactEntity.getMap();
            Map<String, FirstContact> partyFirstContactMap = partyFirstContactEntity.getMap();
            Map<String, PublicReply> adminPublicReplyMap = adminPublicReplyEntity.getMap();
            Map<String, PublicReply> partyPublicReplyMap = partyPublicReplyEntity.getMap();
            Map<String, RepeatPetition> adminRepeatPetitionMap = adminRepeatPetitionEntity.getMap();
            Map<String, RepeatPetition> partyRepeatPetitionMap = partyRepeatPetitionEntity.getMap();
            Map<String, Satisfaction> adminSatisfactionMap = adminSatisfactionEntity.getMap();
            Map<String, Satisfaction> partySatisfactionMap = partySatisfactionEntity.getMap();
            Map<String, Satisfaction> adminRightMap = adminRightEntity.getMap();
            Map<String, Satisfaction> partyRightMap = partyRightEntity.getMap();
            for (Calculated calculated : calculatedList) {
                calculated.setInformRate(null);
                calculated.setFinishRate(null);
                calculated.setInitialAcceptCycle(null);
                calculated.setInitialReplyCycle(null);
                calculated.setInitialContactRate(null);
                calculated.setRepeatPetitionRate(null);
                calculated.setPublicReplyRate(null);
                calculated.setSatisfactionRate(null);
                calculated.setRightRate(null);

                String companyName = calculated.getCompanyName();
                calculating.setCurrentCompanyName(companyName);
                // 按期受理告知率
                CycleAccept adminCycleAccept = adminCycleAcceptMap.get(companyName);
                CycleAccept partyCycleAccept = partyCycleAcceptMap.get(companyName);
                int adminAcceptCount = 0;
                int partyAcceptCount = 0;
                int adminAcceptFinishCount = 0;
                int partyAcceptFinishCount = 0;
                int adminReplyCount = 0;
                int partyReplyCount = 0;
                int adminReplyFinishCount = 0;
                int partyReplyFinishCount = 0;
                if (adminCycleAccept != null) {
                    adminAcceptCount = isnull(adminCycleAccept.getAcceptCount());
                    adminAcceptFinishCount = isnull(adminCycleAccept.getAcceptFinishCount());
                    adminReplyCount = isnull(adminCycleAccept.getReplyCount());
                    adminReplyFinishCount = isnull(adminCycleAccept.getReplyFinishCount());
                }
                if (partyCycleAccept != null) {
                    partyAcceptCount = isnull(partyCycleAccept.getAcceptCount());
                    partyAcceptFinishCount = isnull(partyCycleAccept.getAcceptFinishCount());
                    partyReplyCount = isnull(partyCycleAccept.getReplyCount());
                    partyReplyFinishCount = isnull(partyCycleAccept.getReplyFinishCount());
                }
                int acceptFinishCount = adminAcceptFinishCount + partyAcceptFinishCount;
                int acceptCount = adminAcceptCount + partyAcceptCount;
                int replyCount = adminReplyCount + partyReplyCount;
                int replyFinishCount = adminReplyFinishCount + partyReplyFinishCount;
                BigDecimal informRate = divide(acceptFinishCount, acceptCount);
                calculated.setInformRate(informRate);

                // 按期办结率
                BigDecimal finishRate = divide(replyFinishCount, replyCount);
                calculated.setFinishRate(finishRate);

                // 初次信访平均受理周期 初次信访平均答复周期
                FirstContact adminFirstContact = adminFirstContactMap.get(companyName);
                FirstContact partyFirstContact = partyFirstContactMap.get(companyName);
                BigDecimal adminAcceptTime = null;
                BigDecimal partyAcceptTime = null;
                BigDecimal adminReplyTime = null;
                BigDecimal partyReplyTime = null;
                int adminCount = 0;
                int partyCount = 0;
                if (adminFirstContact != null) {
                    adminAcceptTime = adminFirstContact.getAcceptTime();
                    adminReplyTime = adminFirstContact.getReplyTime();
                    adminCount = isnull(adminFirstContact.getCount());
                }
                if (partyFirstContact != null) {
                    partyAcceptTime = partyFirstContact.getAcceptTime();
                    partyReplyTime = partyFirstContact.getReplyTime();
                    partyCount = isnull(partyFirstContact.getCount());
                }
                BigDecimal firstContactCount = new BigDecimal(adminCount + partyCount);
                BigDecimal initialAcceptCycle = divide(add(multiply(adminAcceptTime, new BigDecimal(adminCount)),
                        multiply(partyAcceptTime, new BigDecimal(partyCount))), firstContactCount);
                BigDecimal initialReplyCycle = divide(add(multiply(adminReplyTime, new BigDecimal(adminCount)),
                        multiply(partyReplyTime, new BigDecimal(partyCount))), firstContactCount);
                calculated.setInitialAcceptCycle(initialAcceptCycle);
                calculated.setInitialReplyCycle(initialReplyCycle);

                // 初次信访办理联系率
                FirstAccept adminFirstAccept = adminFirstAcceptMap.get(companyName);
                FirstAccept partyFirstAccept = partyFirstAcceptMap.get(companyName);
                int adminContactCount = 0;
                int partyContactCount = 0;
                int adminFirstAcceptCount = 0;
                int partyFirstAcceptCount = 0;
                if (adminFirstAccept != null) {
                    adminContactCount = isnull(adminFirstAccept.getContactCount());
                    adminFirstAcceptCount = isnull(adminFirstAccept.getCount());
                }
                if (partyFirstAccept != null) {
                    partyContactCount = isnull(partyFirstAccept.getContactCount());
                    partyFirstAcceptCount = isnull(partyFirstAccept.getCount());
                }
                BigDecimal initialContactRate = divide(adminContactCount + partyContactCount, adminFirstAcceptCount + partyFirstAcceptCount);
                calculated.setInitialContactRate(initialContactRate);

                // 重复信访率
                RepeatPetition adminRepeatPetition = adminRepeatPetitionMap.get(companyName);
                RepeatPetition partyRepeatPetition = partyRepeatPetitionMap.get(companyName);
                int adminFinishCount = 0;
                int partyFinishCount = 0;
                int adminFirstCount = 0;
                int partyFirstCount = 0;
                if (adminRepeatPetition != null) {
                    adminFinishCount = isnull(adminRepeatPetition.getFinishCount());
                    adminFirstCount = isnull(adminRepeatPetition.getFirstCount());
                }
                if (partyRepeatPetition != null) {
                    partyFinishCount = isnull(partyRepeatPetition.getFinishCount());
                    partyFirstCount = isnull(partyRepeatPetition.getFirstCount());
                }
                BigDecimal repeatPetitionRate = divide(adminFirstCount + partyFirstCount, adminFinishCount + partyFinishCount);
                calculated.setRepeatPetitionRate(repeatPetitionRate);

                // 公开回复率
                PublicReply adminPublicReply = adminPublicReplyMap.get(companyName);
                PublicReply partyPublicReply = partyPublicReplyMap.get(companyName);
                int adminPublicCount = 0;
                int partyPublicCount = 0;
                int adminPublicReplyCount = 0;
                int partyPublicReplyCount = 0;
                if (adminPublicReply != null) {
                    adminPublicCount = isnull(adminPublicReply.getPublicCount());
                    adminPublicReplyCount = isnull(adminPublicReply.getCount());
                }
                if (partyPublicReply != null) {
                    partyPublicCount = isnull(partyPublicReply.getPublicCount());
                    partyPublicReplyCount = isnull(partyPublicReply.getCount());
                }
                BigDecimal publicReplyRate = divide(adminPublicCount + partyPublicCount, adminPublicReplyCount + partyPublicReplyCount);
                calculated.setPublicReplyRate(publicReplyRate);

                // 信访工作机构参评满意率 有权处理单位参评满意率
                // 2020-10-08 有权处理单位参评满意率 由单独文件导入
                Satisfaction adminSatisfaction = adminSatisfactionMap.get(companyName);
                Satisfaction partySatisfaction = partySatisfactionMap.get(companyName);
                int adminAllCount = 0;
                int partyAllCount = 0;
                int adminAssessSatisAllCount = 0;
                int partyAssessSatisAllCount = 0;
                int adminAssessSatisSomeCount = 0;
                int partyAssessSatisSomeCount = 0;
                int adminAssessNoSatisCount = 0;
                int partyAssessNoSatisCount = 0;
                int adminOverdueNoAssessCount = 0;
                int partyOverdueNoAssessCount = 0;
                if (adminSatisfaction != null) {
                    adminAllCount = isnull(adminSatisfaction.getAllCount());
                    adminAssessSatisAllCount = isnull(adminSatisfaction.getAssessSatisAllCount());
                    adminAssessSatisSomeCount = isnull(adminSatisfaction.getAssessSatisSomeCount());
                    adminAssessNoSatisCount = isnull(adminSatisfaction.getAssessNoSatisCount());
                    adminOverdueNoAssessCount = isnull(adminSatisfaction.getOverdueNoAssessCount());
                }
                if (partySatisfaction != null) {
                    partyAllCount = isnull(partySatisfaction.getAllCount());
                    partyAssessSatisAllCount = isnull(partySatisfaction.getAssessSatisAllCount());
                    partyAssessSatisSomeCount = isnull(partySatisfaction.getAssessSatisSomeCount());
                    partyAssessNoSatisCount = isnull(partySatisfaction.getAssessNoSatisCount());
                    partyOverdueNoAssessCount = isnull(partySatisfaction.getOverdueNoAssessCount());
                }
                // 参评满意率 = 参评率 * 满意率
                BigDecimal satisfactionParams1 = divide(adminAssessSatisAllCount + adminAssessSatisSomeCount
                        + adminOverdueNoAssessCount + partyAssessSatisAllCount + partyAssessSatisSomeCount
                        + partyOverdueNoAssessCount, adminAllCount + partyAllCount);
                BigDecimal satisfactionParams2 = divide(adminAssessSatisAllCount + adminAssessSatisSomeCount
                        + adminAssessNoSatisCount + partyAssessSatisAllCount + partyAssessSatisSomeCount
                        + partyAssessNoSatisCount, adminAllCount + partyAllCount);

                BigDecimal satisfactionRate = multiply(satisfactionParams1, satisfactionParams2);
                calculated.setSatisfactionRate(satisfactionRate);

                // 有权处理单位参评满意率
                Satisfaction adminSatisfactionRight = adminRightMap.get(companyName);
                Satisfaction partySatisfactionRight = partyRightMap.get(companyName);
                int adminAllCountRight = 0;
                int partyAllCountRight = 0;
                int adminAssessSatisAllCountRight = 0;
                int partyAssessSatisAllCountRight = 0;
                int adminAssessSatisSomeCountRight = 0;
                int partyAssessSatisSomeCountRight = 0;
                int adminAssessNoSatisCountRight = 0;
                int partyAssessNoSatisCountRight = 0;
                int adminOverdueNoAssessCountRight = 0;
                int partyOverdueNoAssessCountRight = 0;
                if (adminSatisfactionRight != null) {
                    adminAllCountRight = isnull(adminSatisfactionRight.getAllCount());
                    adminAssessSatisAllCountRight = isnull(adminSatisfactionRight.getAssessSatisAllCount());
                    adminAssessSatisSomeCountRight = isnull(adminSatisfactionRight.getAssessSatisSomeCount());
                    adminAssessNoSatisCountRight = isnull(adminSatisfactionRight.getAssessNoSatisCount());
                    adminOverdueNoAssessCountRight = isnull(adminSatisfactionRight.getOverdueNoAssessCount());
                }
                if (partySatisfactionRight != null) {
                    partyAllCountRight = isnull(partySatisfactionRight.getAllCount());
                    partyAssessSatisAllCountRight = isnull(partySatisfactionRight.getAssessSatisAllCount());
                    partyAssessSatisSomeCountRight = isnull(partySatisfactionRight.getAssessSatisSomeCount());
                    partyAssessNoSatisCountRight = isnull(partySatisfactionRight.getAssessNoSatisCount());
                    partyOverdueNoAssessCountRight = isnull(partySatisfactionRight.getOverdueNoAssessCount());
                }
                BigDecimal rightRateParams1 = divide(adminAssessSatisAllCountRight + adminAssessSatisSomeCountRight
                        + adminOverdueNoAssessCountRight + partyAssessSatisAllCountRight + partyAssessSatisSomeCountRight
                        + partyOverdueNoAssessCountRight, adminAllCountRight + partyAllCountRight);
                BigDecimal rightRateParams2 = divide(adminAssessSatisAllCountRight + adminAssessSatisSomeCountRight
                        + adminAssessNoSatisCountRight + partyAssessSatisAllCountRight + partyAssessSatisSomeCountRight
                        + partyAssessNoSatisCountRight, adminAllCountRight + partyAllCountRight);
                BigDecimal rightRate = multiply(rightRateParams1, rightRateParams2);
                calculated.setRightRate(rightRate);

//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                calculatedCount++;
                calculating.setCalculatedCount(calculatedCount);
                if (calculatedCount.equals(recordCount)) {
                    calculating.setStatus(CalculatedStatusEnum.CALCULATED);
                }
            }
        } else {
            throw new RuntimeException("请先导入应导入的文档！");
        }
    }

    public static int isnull(Integer integer) {
        return integer == null ? 0 : integer;
    }

    public static BigDecimal add(BigDecimal params, BigDecimal addend) {
        if (params == null) {
            params = BigDecimal.ZERO;
        }
        if (addend == null) {
            addend = BigDecimal.ZERO;
        }
        return params.add(addend);
    }

    public static BigDecimal divide(int divider, int divisor) {
        return divide(new BigDecimal(divider), new BigDecimal(divisor));
    }

    public static BigDecimal divide(BigDecimal divider, BigDecimal divisor) {
        BigDecimal result = null;
        if (divisor != null && BigDecimal.ZERO.compareTo(divisor) != 0) {
            if (divider != null && BigDecimal.ZERO.compareTo(divider) != 0) {
                result = divider.divide(divisor, 4, BigDecimal.ROUND_HALF_UP);
            } else {
                result = BigDecimal.ZERO;
            }
        }
        return result;
    }

    public static BigDecimal multiply(BigDecimal multiply, BigDecimal multiplicand) {
        if (multiply == null || multiplicand == null) {
            return null;
        }
        BigDecimal result =  multiply.multiply(multiplicand);
        return result.setScale(4, RoundingMode.HALF_DOWN);
    }


}
