package com.fyd.cygl;

import com.fyd.cygl.entity.Role;
import com.fyd.cygl.entity.User;
import com.fyd.cygl.service.UserService;
import com.fyd.cygl.service.impl.UserServiceImpl;
import com.fyd.cygl.util.DateUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={CyglApplication.class})// 指定启动类
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Test
    public void test5(){
        User user = userService.findById(1);
        System.out.println(user);
    }
    @Test
    public void test1(){
        User user = userService.findByName("小王");
        System.out.println(user);
    }
    @Test
    public void test2(){
        List<Role> roles = userService.findRoles("admin");
        System.out.println(roles);
    }
    @Test
    public void test3(){
        User user = new User();
        user.setId(4);
        user.setPassword("17ada036d565e513d1b785c440fb51d7");
        user.setUsername("xiaohong");
        user.setRoleId(3);
        user.setCreateTime(DateUtils.getDate2());
        user.setSalt("4bddfbae648b04cecc7fe8d92a1cad18");
        int i = userService.insertUser(user);
        System.out.println(i);
    }
    @Test
    public void test4(){
        //        System.out.println("-----用户注册------");
//        //生成密码盐（部分，需要存入数据库中）
        String sort = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println("sort:"+sort);
        User user = new User();
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        String username="haha";
        String password="123456";
        user.setUsername(username);
        user.setPassword(password);
        user.setSalt(sort);
        System.out.println("username:"+username+"    password:"+password);
        //result加密后的密码
        String result = new SimpleHash("MD5", user.getPassword(),user.getCredentialsSalt() , 2).toString();
//        user.getUsername() + sort
//        System.out.println("1:"+user.getUsername()+sort);
//        System.out.println("1:"+user.getCredentialsSalt());
        String result2=new SimpleHash("MD5", user.getPassword(),user.getCredentialsSalt() , 2).toString();
        System.out.println("2"+result);
        System.out.println("3"+result2);
//        user.setId(2);
        user.setPassword(result);
//        user.setSalt(sort);
        user.setCreateTime(DateUtils.getDate2());
        user.setRoleId(1);
        System.out.println(user);
//        UserService userService = new UserServiceImpl();
//        System.out.println("开始进行插入");
//        int insertResult = userService.insertUser(user);
//        System.out.println("退出插入");
    }

}
