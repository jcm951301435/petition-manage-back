package com.ssy.petition.service.petition;


import com.ssy.petition.dto.petition.params.PetitionContradictionEditParams;
import com.ssy.petition.dto.petition.params.PetitionContradictionParams;
import com.ssy.petition.dto.petition.result.PetitionContradictionResult;

import java.util.List;

/**
 * 权限业务层
 *
 * @author jcm
 */
public interface PetitionContradictionService {

    List<PetitionContradictionResult> list(PetitionContradictionParams params, Integer pageNum, Integer pageSize);

    Long create(PetitionContradictionEditParams params);

    int update(PetitionContradictionEditParams params);

    int delete(Long id);

    int disable(Long id);

}
