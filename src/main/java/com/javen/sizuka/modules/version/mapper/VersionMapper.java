package com.javen.sizuka.modules.version.mapper;

import com.javen.sizuka.model.Version;
import com.javen.sizuka.model.VersionExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VersionMapper {
    long countByExample(VersionExample example);

    int deleteByExample(VersionExample example);

    int deleteByPrimaryKey(String version);

    int insert(Version record);

    int insertSelective(Version record);

    List<Version> selectByExampleWithBLOBs(VersionExample example);

    List<Version> selectByExample(VersionExample example);

    Version selectByPrimaryKey(String version);

    int updateByExampleSelective(@Param("record") Version record, @Param("example") VersionExample example);

    int updateByExampleWithBLOBs(@Param("record") Version record, @Param("example") VersionExample example);

    int updateByExample(@Param("record") Version record, @Param("example") VersionExample example);

    int updateByPrimaryKeySelective(Version record);

    int updateByPrimaryKeyWithBLOBs(Version record);

    int updateByPrimaryKey(Version record);

    Version lastVersion();
}