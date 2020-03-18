package com.fyd.cygl.dao;

import com.fyd.cygl.entity.Staffs;
import com.fyd.cygl.entity.StaffsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StaffsMapper {
    int countByExample(StaffsExample example);

    int deleteByExample(StaffsExample example);

    int deleteByPrimaryKey(Integer sid);

    int insert(Staffs record);

    int insertSelective(Staffs record);

    List<Staffs> selectByExample(StaffsExample example);

    Staffs selectByPrimaryKey(Integer sid);

    int updateByExampleSelective(@Param("record") Staffs record, @Param("example") StaffsExample example);

    int updateByExample(@Param("record") Staffs record, @Param("example") StaffsExample example);

    int updateByPrimaryKeySelective(Staffs record);

    int updateByPrimaryKey(Staffs record);
}