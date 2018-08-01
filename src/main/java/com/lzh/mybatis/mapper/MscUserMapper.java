package com.lzh.mybatis.mapper;

import com.lzh.mybatis.entity.MscUser;
import com.lzh.mybatis.entity.MscUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MscUserMapper {
    int countByExample(MscUserExample example);

    int deleteByExample(MscUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MscUser record);

    int insertSelective(MscUser record);

    List<MscUser> selectByExample(MscUserExample example);

    MscUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MscUser record, @Param("example") MscUserExample example);

    int updateByExample(@Param("record") MscUser record, @Param("example") MscUserExample example);

    int updateByPrimaryKeySelective(MscUser record);

    int updateByPrimaryKey(MscUser record);
}