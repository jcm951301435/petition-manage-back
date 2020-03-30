package com.ssy.petition.dao.petition;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.entity.petition.PetitionContradictionContent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetitionContradictionContentMapper extends BaseMapper<PetitionContradictionContent> {

    List<PetitionContradictionContent> getListByContradictionId(Long contradictionId);

    int deleteByContradictionId(Long contradictionId);

    int deleteByContradictionIdAndNotExists(@Param("contradictionId")Long contradictionId,
                                            @Param("PetitionContradictionContents")List<PetitionContradictionContent> petitionContradictionContents);

    int insertList(@Param("list")List<PetitionContradictionContent> list);

}