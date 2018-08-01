package com.lzh.mybatis.mapper;

import com.lzh.mybatis.entity.MscRole;
import com.lzh.mybatis.entity.MscRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MscRoleMapper {
    int countByExample(MscRoleExample example);

    int deleteByExample(MscRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MscRole record);

    int insertSelective(MscRole record);

    List<MscRole> selectByExample(MscRoleExample example);

    MscRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MscRole record, @Param("example") MscRoleExample example);

    int updateByExample(@Param("record") MscRole record, @Param("example") MscRoleExample example);

    int updateByPrimaryKeySelective(MscRole record);

    int updateByPrimaryKey(MscRole record);
    
    //用户管理专用
    List<MscRole> getAlreadHaveRolesByUserId(Long userId);
    List<MscRole> getNotHaveRoleByUserId(Long userId);
    
}