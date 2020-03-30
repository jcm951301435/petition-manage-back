package com.ssy.petition.dao.petition;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.entity.petition.PetitionContradictionResolveProcess;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetitionContradictionResolveProcessMapper extends BaseMapper<PetitionContradictionResolveProcess> {

    List<PetitionContradictionResolveProcess> getListByContradictionId(Long contradictionId);

    int deleteByContradictionId(Long contradictionId);

    int deleteByContradictionIdAndNotExists(@Param("contradictionId")Long contradictionId,
                                            @Param("petitionContradictionResolveProcesses")List<PetitionContradictionResolveProcess> petitionContradictionResolveProcesses);

    int insertList(@Param("list")List<PetitionContradictionResolveProcess> list);

}