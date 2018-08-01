package com.lzh.mybatis.mapper;

import com.lzh.mybatis.entity.MscPerm;
import com.lzh.mybatis.entity.MscPermExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MscPermMapper {
    int countByExample(MscPermExample example);

    int deleteByExample(MscPermExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MscPerm record);

    int insertSelective(MscPerm record);

    List<MscPerm> selectByExample(MscPermExample example);

    MscPerm selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MscPerm record, @Param("example") MscPermExample example);

    int updateByExample(@Param("record") MscPerm record, @Param("example") MscPermExample example);

    int updateByPrimaryKeySelective(MscPerm record);

    int updateByPrimaryKey(MscPerm record);
    
    /**
     * 新增权限控制查询方法
     * */
    List<MscPerm> selectForSercurity(Long id);
    
    //用户管理专用
    List<MscPerm> getAlreadHavePermsByRoleId(Long roleId);
    List<MscPerm> getNotHavePermsByRoleId(Long roleId);
}