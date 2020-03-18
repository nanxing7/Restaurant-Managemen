package com.fyd.cygl.service.impl;

import com.fyd.cygl.dao.TablesMapper;
import com.fyd.cygl.entity.Tables;
import com.fyd.cygl.entity.TablesExample;
import com.fyd.cygl.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TablesServiceImpl implements TablesService {
    @Autowired
    private TablesMapper tablesMapper;
    @Autowired
    private TablesExample tablesExample;
    @Override
    public List<Tables> searchAll() {
        List<Tables> tables = tablesMapper.selectByExample(null);
        return tables;
    }

    @Override
    public int deleteBytid(int tid) {
        int i = tablesMapper.deleteByPrimaryKey(tid);
        return i;
    }

    @Override
    public int updateBytid(Tables tables) {
        int i = tablesMapper.updateByPrimaryKey(tables);
        return i;
    }

    @Override
    public int add(Tables tables) {
        int insert = tablesMapper.insert(tables);
        return insert;
    }

    @Override
    public List<Tables> searchByRoom(String room) {
        tablesExample.clear();
        TablesExample.Criteria criteria = tablesExample.createCriteria().andRoomLike(room);
        List<Tables> tables = tablesMapper.selectByExample(tablesExample);
        tablesExample.clear();
        return tables;
    }

    @Override
    public List<Tables> searchByTid(Integer tid) {
        tablesExample.clear();
        TablesExample.Criteria criteria = tablesExample.createCriteria().andTidEqualTo(tid);
        List<Tables> tables = tablesMapper.selectByExample(tablesExample);
        tablesExample.clear();
        return tables;
    }

    @Override
    public List<Tables> searchBystatus(Integer status) {
        tablesExample.clear();
        TablesExample.Criteria criteria = tablesExample.createCriteria().andStatusEqualTo(status);
        List<Tables> tables = tablesMapper.selectByExample(tablesExample);
        tablesExample.clear();
        return tables;
    }


}
