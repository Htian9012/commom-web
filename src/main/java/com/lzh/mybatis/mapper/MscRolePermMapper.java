package com.lzh.mybatis.mapper;

import com.lzh.mybatis.entity.MscRolePerm;
import com.lzh.mybatis.entity.MscRolePermExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MscRolePermMapper {
    int countByExample(MscRolePermExample example);

    int deleteByExample(MscRolePermExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MscRolePerm record);

    int insertSelective(MscRolePerm record);

    List<MscRolePerm> selectByExample(MscRolePermExample example);

    MscRolePerm selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MscRolePerm record, @Param("example") MscRolePermExample example);

    int updateByExample(@Param("record") MscRolePerm record, @Param("example") MscRolePermExample example);

    int updateByPrimaryKeySelective(MscRolePerm record);

    int updateByPrimaryKey(MscRolePerm record);
}