package com.fyd.cygl.service;

import com.fyd.cygl.entity.*;

import java.util.List;

public interface UserService {
    //根据名字查询用户
    public User findByName(String name);
    public User findById(Integer id);
    //根据用户名查询所有的角色信息
    public List<Role> findRoles(String  name);
    //根据用户的id查询所有权限信息
    public List<Permission> findPermissions(String name);
    //根据userid查询用户
    public User findUserByUsername(String Username);
    //分配默认的角色权限
    public int assignDefaultUserRolePermission(User user);
    //插入用户
    public int insertUser(User user);
    //插入用户角色
    public int insertUserRole(UserRole userRole);
    //查询所有用户
    public List<User> selectAll();
    //更新user
    public int updateUser(User user);
    //更新userRole
    public int updateUserRole(UserRoleKey userRoleKey,UserRole userRole);
    //查找userrole
    public UserRole findByUidAndRid(UserRoleKey userRoleKey);
    //删除user通过id
    public int deleteById(Integer id);
    //删除userrole通过uid,rid
    public int deleteByUidAndRid(UserRoleKey userRoleKey);
}
