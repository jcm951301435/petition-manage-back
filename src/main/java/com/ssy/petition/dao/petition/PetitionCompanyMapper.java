package com.ssy.petition.dao.petition;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.dto.petition.params.PetitionCompanyParams;
import com.ssy.petition.dto.petition.result.PetitionCompanyResult;
import com.ssy.petition.entity.petition.PetitionCompany;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetitionCompanyMapper extends BaseMapper<PetitionCompany> {

    List<PetitionCompanyResult> getList(@Param("queryParam") PetitionCompanyParams params);

}
