package com.fyd.cygl;

import com.fyd.cygl.entity.Orders;
import com.fyd.cygl.service.OrdersService;
import com.fyd.cygl.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={CyglApplication.class})// 指定启动类
public class OrderTest {
    @Autowired
    OrdersService ordersService;
    @Test
    public void test1(){
        Orders orders = ordersService.selectByTidAndOrderStatus(2, 0);
        System.out.println(orders);
    }
    @Test
    public void test2(){
        List<Orders> orders = ordersService.selectByYearAndMonth("2020-03");
        System.out.println(orders);
    }
}
