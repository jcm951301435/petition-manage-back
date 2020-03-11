package com.ssy.petition.dao.petition;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.dto.petition.params.PetitionTroubleshootParams;
import com.ssy.petition.dto.petition.result.PetitionTroubleshootResult;
import com.ssy.petition.entity.petition.PetitionTroubleshoot;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetitionTroubleshootMapper extends BaseMapper<PetitionTroubleshoot> {

    List<PetitionTroubleshootResult> getList(@Param("queryParam") PetitionTroubleshootParams params);

    int insertList(@Param("list")List<PetitionTroubleshoot> list);

}