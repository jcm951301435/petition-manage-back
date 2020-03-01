package com.ssy.petition.dao.petition;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.entity.petition.PetitionContradictionResolveProcess;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetitionContradictionResolveProcessMapper extends BaseMapper<PetitionContradictionResolveProcess> {

    List<PetitionContradictionResolveProcess> getListByContradictionId(Long contradictionId);

}