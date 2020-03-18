package com.fyd.cygl.service.impl;

import com.fyd.cygl.dao.OrdersMapper;
import com.fyd.cygl.entity.Orders;
import com.fyd.cygl.entity.OrdersExample;
import com.fyd.cygl.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    OrdersExample ordersExample;
    @Override
    public List<Orders> selectAll() {
        return ordersMapper.selectByExample(null);
    }

    @Override
    public int addOrder(Orders orders) {
        int insert = ordersMapper.insert(orders);
        return insert;
    }

    @Override
    public Orders selectByOid(String oid) {
        Orders orders = ordersMapper.selectByPrimaryKey(oid);
        return orders;
    }

    @Override
    public Orders selectByTidAndOrderStatus(Integer tid, Integer status) {
        ordersExample.clear();
        OrdersExample.Criteria criteria = ordersExample.createCriteria().andTidEqualTo(tid).andStatusEqualTo(status);
        List<Orders> orders = ordersMapper.selectByExample(ordersExample);
        ordersExample.clear();
        if (orders.size()!=0){
            return orders.get(0);
        }else {
            return null;
        }

    }

    @Override
    public int update(Orders orders) {
        int i = ordersMapper.updateByPrimaryKey(orders);
        return i;
    }

    @Override
    public int deleteorderByOid(String oid) {
        int i = ordersMapper.deleteByPrimaryKey(oid);
        return i;
    }

    @Override
    public List<Orders> selectByStatus(Integer status) {
       OrdersExample oe = new OrdersExample();
        OrdersExample.Criteria criteria = oe.createCriteria().andStatusEqualTo(status);
        List<Orders> orders = ordersMapper.selectByExample(oe);
        return orders;
    }

    @Override
    public List<Orders> selectByYearAndMonth(String month) {
        List<Orders> orders1 = ordersMapper.selectByYearAndMonth(month);
        return orders1;
    }
}
