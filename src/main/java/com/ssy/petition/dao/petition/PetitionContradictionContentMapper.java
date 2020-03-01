package com.ssy.petition.dao.petition;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.entity.petition.PetitionContradictionContent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetitionContradictionContentMapper extends BaseMapper<PetitionContradictionContent> {

    List<PetitionContradictionContent> getListByContradictionId(Long contradictionId);

}