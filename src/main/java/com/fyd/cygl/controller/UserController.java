package com.fyd.cygl.controller;

import com.fyd.cygl.entity.User;
import com.fyd.cygl.entity.UserRole;
import com.fyd.cygl.entity.UserRoleKey;
import com.fyd.cygl.service.UserService;
import com.fyd.cygl.util.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @ResponseBody
    @PostMapping("/users")
    public Map<String, Object> selectAllUser(@RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                             //limit：每页数据量
                                             @RequestParam(name = "limit",required = false, defaultValue = "10")Integer limit){
        PageHelper.startPage(page, limit);
        Map<String,Object> result = new HashMap<>();
        List<User> users = userService.selectAll();
        for(User user:users){
            user.setTimeformat();
            user.setRolename();
        }
        PageInfo<User> pageInfo = new PageInfo<>(users);
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("data",users);
        return result;
    }

    @ResponseBody
    @PostMapping("/adduser")
    public int insertuser(@RequestParam(name = "username",required = false)String username,
                          @RequestParam(name = "password",required = false)String password,
                          @RequestParam(name = "roleid",required = false)Integer roleid,Map<String, Object> data){
        //-----对user表的操作--------
        //生成密码盐（部分，需要存入数据库中）
        String sort = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println("sort:"+sort);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSalt(sort);
        System.out.println("username:"+username+"    password:"+password);
        //result加密后的密码
        String result = new SimpleHash("MD5", user.getPassword(),user.getCredentialsSalt() , 2).toString();
        System.out.println("2"+result);
        user.setPassword(result);
        user.setCreateTime(DateUtils.getDate2());
        user.setRoleId(roleid);
        int i = userService.insertUser(user);
        if (i==0){
            return 0;
        }

        //-----对user_role表的操作--------
        UserRole userRole = new UserRole();
        if (roleid==1){
            userRole.setRemarks(username+"是超级管理员");
        }else if (roleid==2){
            userRole.setRemarks(username+"是老板");
        }else if (roleid==3){
            userRole.setRemarks(username+"是员工");
        }
        Integer user_id = userService.findByName(username).getId();
        userRole.setUserId(user_id);
        userRole.setRoleId(roleid);
        System.out.println(user);
        int i1 = userService.insertUserRole(userRole);
        if (i1==0){
            return 0;
        }


        return 1;
    }
    //检查原始密码是否和数据库一致
    @ResponseBody
    @PostMapping("/checkoldpw")
    public int checkOldPw(@RequestParam(name = "username",required = false)String username,
                          @RequestParam(name = "oldpw",required = false)String oldpw){
        User user = userService.findByName(username);
        //用户输入的加密结果
        String uresult = new SimpleHash("MD5", oldpw,user.getCredentialsSalt() , 2).toString();
        if (user.getPassword().equals(uresult)){
            return 1;
        }
        return 0;

    }
    @ResponseBody
    @PostMapping("/updateuser")
    public int updateuser(@RequestParam(name = "id",required = false)Integer id,
                          @RequestParam(name = "newpassword2",required = false)String newpassword2,
                          @RequestParam(name = "roleid2",required = false)Integer roleid2){
        User user = userService.findById(id);
        UserRoleKey userRoleKey = new UserRoleKey();
        userRoleKey.setUserId(id);
        userRoleKey.setRoleId(user.getRoleId());
        //用户输入的加密结果
        String uresult = new SimpleHash("MD5", newpassword2,user.getCredentialsSalt() , 2).toString();
        user.setPassword(uresult);
        user.setRoleId(roleid2);
        int i = userService.updateUser(user);
        if (i==0){
            return 0;
        }



        UserRole userRole = userService.findByUidAndRid(userRoleKey);
        userRole.setRoleId(roleid2);
        userRole.setUserId(id);
        if (roleid2==1){
            userRole.setRemarks(user.getUsername()+"是超级管理员");
        }else if (roleid2==2){
            userRole.setRemarks(user.getUsername()+"是老板");
        }else if (roleid2==3){
            userRole.setRemarks(user.getUsername()+"是员工");
        }
        int i1 = userService.updateUserRole(userRoleKey, userRole);
        if (i1==0){
            return 0;
        }
        return 1;
    }
    @ResponseBody
    @PostMapping("/deleteuser")
    public int deleteUserByid(@RequestParam(name = "id",required = false)Integer id,
                              @RequestParam(name = "roleid",required = false)Integer roleid){
        UserRoleKey userRoleKey = new UserRoleKey();
        userRoleKey.setRoleId(roleid);
        userRoleKey.setUserId(id);
        System.out.println(userRoleKey);
        //删除user表的当前条信息
        int i = userService.deleteById(id);
        if (i==0){
            return 0;
        }
        //删除userrole表的当前条信息
        int i1 = userService.deleteByUidAndRid(userRoleKey);
        if (i1==0){
            return 0;
        }
        return 1;
    }
}
