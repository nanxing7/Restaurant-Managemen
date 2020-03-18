package com.fyd.cygl;

import com.fyd.cygl.entity.Foods;
import com.fyd.cygl.service.FoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={CyglApplication.class})// 指定启动类
public class FoodsServiceTest {
    @Autowired
    FoodsService foodsService;
    @Autowired

    @Test
    public void test(){
        List<Foods> foods = foodsService.selectAll();
        foods.forEach(item -> {
            System.out.println(item.toString());
        });
    }
    @Test
    public void test2(){
        List<Foods> foods = foodsService.selectAll();
        foods.forEach(item -> {
            System.out.println(item.toString());
        });
    }
    @Test
    public void test3(){
        Foods foods = new Foods();
        foods.setName("香菜拌木耳");
        foods.setPrice(20.0);


            foods.setSort("凉菜");
            foods.setStatus("有");
        foods.setResult("能益气强身，有活血效能，并可防治缺铁性贫血等；可养血驻颜，令人肌肤红润，容光焕发，能够疏通肠胃，润滑肠道，同时对高血压患者也有一定帮助。味道鲜美，可素可荤，营养丰富。");
        foods.setImage("/images/xiangcaibanmuer.jpg");
        int add = foodsService.add(foods);
        System.out.println(add);
    }
}
