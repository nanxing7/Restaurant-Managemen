package com.fyd.cygl.service;

import com.fyd.cygl.entity.Staffs;

import java.util.List;

public interface StaffsService {
    public List<Staffs> searchAll();

    public int deleteBysid(int sid);

    public int updateBysid(Staffs staffs);

    public int add(Staffs staffs);

    public List<Staffs> searchBySid(Integer sid);

    public List<Staffs> searchByName(String name);
}
