package com.ssy.petition.dao.petition;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.entity.petition.PetitionContradictionResolveReason;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetitionContradictionResolveReasonMapper extends BaseMapper<PetitionContradictionResolveReason>  {

    List<PetitionContradictionResolveReason> getListByContradictionId(Long contradictionId);

    int deleteByContradictionId(Long contradictionId);

    int deleteByContradictionIdAndNotExists(@Param("contradictionId")Long contradictionId,
                                            @Param("petitionContradictionResolveReasons")List<PetitionContradictionResolveReason> petitionContradictionResolveReasons);


}