package com.ssy.petition.service.petition;

import com.ssy.petition.dto.petition.params.PetitionCompanyParams;
import com.ssy.petition.dto.petition.result.PetitionCompanyResult;
import com.ssy.petition.entity.petition.PetitionCompany;

import java.util.List;

public interface PetitionCompanyService {

    List<PetitionCompanyResult> list(PetitionCompanyParams params, Integer pageNum, Integer pageSize);

    int create(PetitionCompany company);

    int update(PetitionCompany company);

    int delete(Long id);

    int disable(Long id);

    List<PetitionCompany> listByName(String companyName);

    Long getCompanyIdByName(String companyName);

    List<PetitionCompanyResult> listAll();

}
