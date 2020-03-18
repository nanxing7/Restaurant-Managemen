package com.fyd.cygl.dao;

import com.fyd.cygl.entity.Foods;
import com.fyd.cygl.entity.FoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FoodsMapper {
    int countByExample(FoodsExample example);

    int deleteByExample(FoodsExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Foods record);

    int insertSelective(Foods record);

    List<Foods> selectByExample(FoodsExample example);

    Foods selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") Foods record, @Param("example") FoodsExample example);

    int updateByExample(@Param("record") Foods record, @Param("example") FoodsExample example);

    int updateByPrimaryKeySelective(Foods record);

    int updateByPrimaryKey(Foods record);
}