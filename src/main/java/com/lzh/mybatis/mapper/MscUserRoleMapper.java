package com.lzh.mybatis.mapper;

import com.lzh.mybatis.entity.MscUserRole;
import com.lzh.mybatis.entity.MscUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MscUserRoleMapper {
    int countByExample(MscUserRoleExample example);

    int deleteByExample(MscUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MscUserRole record);

    int insertSelective(MscUserRole record);

    List<MscUserRole> selectByExample(MscUserRoleExample example);

    MscUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MscUserRole record, @Param("example") MscUserRoleExample example);

    int updateByExample(@Param("record") MscUserRole record, @Param("example") MscUserRoleExample example);

    int updateByPrimaryKeySelective(MscUserRole record);

    int updateByPrimaryKey(MscUserRole record);
}