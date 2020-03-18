package com.fyd.cygl.service;

import com.fyd.cygl.entity.Foods;

import java.util.List;

public interface FoodsService {
    public List<Foods> selectAll();

    public Foods selectByID(int id);

    public int add(Foods foods);

    public int editByfid(Foods foods);

    public int delteByfid(Integer fid);

    public List<Foods> selectBySort(String sort);

    public List<Foods> selectByStatus(String status);

    public List<Foods> selectBySearchLike(String likeContent);

    public Foods selectByName(String name);
}
