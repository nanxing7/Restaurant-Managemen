package com.fyd.cygl.service;

import com.fyd.cygl.entity.Tables;

import java.util.List;

public interface TablesService {
    public List<Tables> searchAll();

    public int deleteBytid(int tid);

    public int updateBytid(Tables tables);

    public int add(Tables tables);

    public List<Tables> searchByRoom(String room);

    public List<Tables> searchByTid(Integer tid);

    public List<Tables> searchBystatus(Integer status);

}
