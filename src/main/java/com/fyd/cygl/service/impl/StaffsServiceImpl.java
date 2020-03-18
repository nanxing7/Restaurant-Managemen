package com.fyd.cygl.service.impl;

import com.fyd.cygl.dao.StaffsMapper;
import com.fyd.cygl.entity.Staffs;
import com.fyd.cygl.entity.StaffsExample;
import com.fyd.cygl.service.StaffsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffsServiceImpl implements StaffsService {
    @Autowired
    StaffsMapper staffsMapper;
    @Autowired
    StaffsExample staffsExample;
    @Override
    public List<Staffs> searchAll() {
        List<Staffs> staffs = staffsMapper.selectByExample(null);
        return staffs;
    }

    @Override
    public int deleteBysid(int sid) {
        int i = staffsMapper.deleteByPrimaryKey(sid);
        return i;
    }

    @Override
    public int updateBysid(Staffs staffs) {
        int i = staffsMapper.updateByPrimaryKey(staffs);
        return i;
    }

    @Override
    public int add(Staffs staffs) {
        int insert = staffsMapper.insert(staffs);
        return insert;
    }

    @Override
    public List<Staffs> searchBySid(Integer sid) {
        staffsExample.clear();
        StaffsExample.Criteria criteria = staffsExample.createCriteria().andSidEqualTo(sid);
        List<Staffs> staffs = staffsMapper.selectByExample(staffsExample);
        staffsExample.clear();
        return staffs;
    }

    @Override
    public List<Staffs> searchByName(String name) {
        staffsExample.clear();
        StaffsExample.Criteria criteria = staffsExample.createCriteria().andNameLike(name);
        List<Staffs> staffs = staffsMapper.selectByExample(staffsExample);
        staffsExample.clear();
        return staffs;
    }
}
