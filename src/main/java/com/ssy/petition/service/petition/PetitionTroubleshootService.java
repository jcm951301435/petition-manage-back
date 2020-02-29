package com.ssy.petition.service.petition;


import com.ssy.petition.dto.petition.params.PetitionTroubleshootParams;
import com.ssy.petition.dto.petition.result.PetitionTroubleshootResult;
import com.ssy.petition.entity.petition.PetitionTroubleshoot;

import java.util.List;

/**
 * 权限业务层
 *
 * @author jcm
 */
public interface PetitionTroubleshootService {

    List<PetitionTroubleshootResult> list(PetitionTroubleshootParams params, Integer pageNum, Integer pageSize);

    int create(PetitionTroubleshoot params);

    int update(PetitionTroubleshoot params);

    int delete(Long id);

    int disable(Long id);

}
