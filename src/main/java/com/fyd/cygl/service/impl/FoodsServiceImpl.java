package com.fyd.cygl.service.impl;

import com.fyd.cygl.dao.FoodsMapper;
import com.fyd.cygl.entity.Foods;
import com.fyd.cygl.entity.FoodsExample;
import com.fyd.cygl.service.FoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FoodsServiceImpl implements FoodsService {
    @Autowired
    private FoodsMapper foodsMapper;
    @Autowired
    private FoodsExample foodsExample;
    @Override
    public List<Foods> selectAll() {
        List<Foods> foods = foodsMapper.selectByExample(null);
        return foods;
    }

    @Override
    public Foods selectByID(int id) {
        return null;
    }

    @Override
    public int add(Foods foods) {
        int insert = foodsMapper.insert(foods);
        System.out.println("insert:"+insert);
        return insert;
    }

    @Override
    public int editByfid(Foods foods) {
        int i = foodsMapper.updateByPrimaryKey(foods);
        return i;
    }

    @Override
    public int delteByfid(Integer fid) {
        int i = foodsMapper.deleteByPrimaryKey(fid);
        return i;
    }

    @Override
    public List<Foods> selectBySort(String sort) {
        foodsExample.clear();
        FoodsExample.Criteria criteria = foodsExample.createCriteria().andSortEqualTo(sort);
        List<Foods> foods = foodsMapper.selectByExample(foodsExample);
        foodsExample.clear();
        return foods;
    }

    @Override
    public List<Foods> selectByStatus(String status) {
        foodsExample.clear();
        FoodsExample.Criteria criteria = foodsExample.createCriteria().andStatusEqualTo(status);
        List<Foods> foods = foodsMapper.selectByExample(foodsExample);
        foodsExample.clear();
        return foods;
    }

    @Override
    public List<Foods> selectBySearchLike(String likeContent) {
        foodsExample.clear();
        FoodsExample.Criteria criteria = foodsExample.createCriteria().andNameLike(likeContent);
        List<Foods> foods = foodsMapper.selectByExample(foodsExample);
        foodsExample.clear();
        return foods;
    }

    @Override
    public Foods selectByName(String name) {
        foodsExample.clear();
        FoodsExample.Criteria criteria = foodsExample.createCriteria().andNameEqualTo(name);
        List<Foods> foods = foodsMapper.selectByExample(foodsExample);
        foodsExample.clear();
        return foods.get(0);
    }

//    @Override
//    public Foods selectByID(int id) {
////        FoodsExample.Criteria criteria = foodsExample.createCriteria();
////        FoodsExample.Criteria criteria1 = criteria.andFidEqualTo(1);
//        return null;
//    }
}
