package com.fyd.cygl.service;


import com.fyd.cygl.entity.Orders;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.core.annotation.Order;

import java.util.Date;
import java.util.List;

public interface OrdersService {

    public List<Orders> selectAll();

    public int addOrder(Orders orders);

    public Orders selectByOid(String oid);

    public Orders selectByTidAndOrderStatus(Integer tid,Integer status);

    public int update(Orders orders);

    public int deleteorderByOid(String oid);

    public List<Orders> selectByStatus(Integer status);

    public List<Orders> selectByYearAndMonth(String month);

}
