package com.ssy.petition.dao.petition;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.dto.petition.params.PetitionContradictionParams;
import com.ssy.petition.dto.petition.result.PetitionContradictionResult;
import com.ssy.petition.entity.petition.PetitionContradiction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetitionContradictionMapper extends BaseMapper<PetitionContradiction> {

    List<PetitionContradictionResult> getList(@Param("queryParam") PetitionContradictionParams params);

}