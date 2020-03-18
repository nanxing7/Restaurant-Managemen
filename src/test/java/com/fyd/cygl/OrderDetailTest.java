package com.fyd.cygl;

import com.fyd.cygl.service.OrderDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={CyglApplication.class})// 指定启动类
public class OrderDetailTest {
    @Autowired
    OrderDetailService orderDetailService;
    @Test
    public void test1(){
//        orderDetailService.
    }
    @Test
    public void test2(){
        int i = orderDetailService.deleteorderdetailByOid("158367697797399655");
        System.out.println(i);
    }
}
