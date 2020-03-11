package com.ssy.petition.service.petition;


import com.ssy.petition.dto.petition.params.PetitionContradictionEditParams;
import com.ssy.petition.dto.petition.params.PetitionContradictionParams;
import com.ssy.petition.dto.petition.result.PetitionContradictionResult;
import com.ssy.petition.entity.petition.PetitionContradiction;

import java.util.List;

/**
 * 权限业务层
 *
 * @author jcm
 */
public interface PetitionContradictionService {

    List<PetitionContradictionResult> list(PetitionContradictionParams params, Integer pageNum, Integer pageSize);

    List<String> applyNameList();

    Long create(PetitionContradictionEditParams params);

    int update(PetitionContradictionEditParams params);

    int delete(Long id);

    int disable(Long id);

    PetitionContradiction transFromPetitionContradictionResult(PetitionContradictionResult result);

    int insertResultList(List<PetitionContradictionResult> resultList);

    int insertList(List<PetitionContradiction> list);

}
