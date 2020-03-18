package com.fyd.cygl.service;

import com.fyd.cygl.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    public int insertOrderDetail(OrderDetail orderDetail);

    public List<OrderDetail> selectByOid(String oid);

    public int deleteByOidAndFnameAndNumber(String oid,String fname,Integer number);

    public int update(OrderDetail orderDetail);

    public Double countFoodPrice(String oid);

    public int deleteorderdetailByOid(String oid);
}
