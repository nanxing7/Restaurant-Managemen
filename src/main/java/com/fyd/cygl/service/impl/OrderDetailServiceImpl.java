package com.fyd.cygl.service.impl;

import com.fyd.cygl.dao.OrderDetailMapper;
import com.fyd.cygl.entity.OrderDetail;
import com.fyd.cygl.entity.OrderDetailExample;
import com.fyd.cygl.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailMapper orderDetailMapper;
    @Autowired
    OrderDetailExample orderDetailExample;

    @Override
    public int insertOrderDetail(OrderDetail orderDetail) {
        int insert = orderDetailMapper.insert(orderDetail);
        return insert;
    }

    @Override
    public List<OrderDetail> selectByOid(String oid) {
        orderDetailExample.clear();
        OrderDetailExample.Criteria criteria = orderDetailExample.createCriteria().andOidEqualTo(oid);
        List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(orderDetailExample);
        orderDetailExample.clear();
        return orderDetails;
    }

    @Override
    public int deleteByOidAndFnameAndNumber(String oid, String fname, Integer number) {
        orderDetailExample.clear();
        OrderDetailExample.Criteria criteria = orderDetailExample.createCriteria().andOidEqualTo(oid).andFnameEqualTo(fname).andNumberEqualTo(number);
        int i = orderDetailMapper.deleteByExample(orderDetailExample);
        orderDetailExample.clear();
        return i;
    }

    @Override
    public int update(OrderDetail orderDetail) {
        int i = orderDetailMapper.updateByPrimaryKey(orderDetail);
        return i;
    }

    @Override
    public Double countFoodPrice(String oid) {
        orderDetailExample.clear();
        OrderDetailExample.Criteria criteria = orderDetailExample.createCriteria().andOidEqualTo(oid);
        List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(orderDetailExample);
        Double preprice=0.0;
        for (int i=0;i<orderDetails.size();i++){
            preprice+=orderDetails.get(i).getPrice();
        }
        orderDetailExample.clear();
        return preprice;
    }

    @Override
    public int deleteorderdetailByOid(String oid) {
        orderDetailExample.clear();
        OrderDetailExample.Criteria criteria = orderDetailExample.createCriteria().andOidEqualTo(oid);
        int i = orderDetailMapper.deleteByExample(orderDetailExample);
        orderDetailExample.clear();
        return i;
    }

}
