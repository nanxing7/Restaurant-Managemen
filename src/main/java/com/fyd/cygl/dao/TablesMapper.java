package com.fyd.cygl.dao;

import com.fyd.cygl.entity.Tables;
import com.fyd.cygl.entity.TablesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TablesMapper {
    int countByExample(TablesExample example);

    int deleteByExample(TablesExample example);

    int deleteByPrimaryKey(Integer tid);

    int insert(Tables record);

    int insertSelective(Tables record);

    List<Tables> selectByExample(TablesExample example);

    Tables selectByPrimaryKey(Integer tid);

    int updateByExampleSelective(@Param("record") Tables record, @Param("example") TablesExample example);

    int updateByExample(@Param("record") Tables record, @Param("example") TablesExample example);

    int updateByPrimaryKeySelective(Tables record);

    int updateByPrimaryKey(Tables record);
}