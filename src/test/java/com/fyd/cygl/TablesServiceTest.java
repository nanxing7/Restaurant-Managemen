package com.fyd.cygl;

import com.fyd.cygl.entity.Tables;
import com.fyd.cygl.service.TablesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={CyglApplication.class})// 指定启动类
public class TablesServiceTest {
    @Autowired
    TablesService tablesService;

    @Test
    public void test1(){
        List<Tables> tables = tablesService.searchAll();
        System.out.println(tables);
    }
}
