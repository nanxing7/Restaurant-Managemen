package com.fyd.cygl.service.impl;

import com.fyd.cygl.dao.*;
import com.fyd.cygl.entity.*;
import com.fyd.cygl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public User findByName(String name) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria().andUsernameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        return users.get(0);
    }

    @Override
    public User findById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    //根据用户名查询所有的角色信息
    @Override
    public List<Role> findRoles(String name) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria1 = userExample.createCriteria().andUsernameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        Integer id = users.get(0).getId();

        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria().andUserIdEqualTo(id);
        List<UserRole> keyList = userRoleMapper.selectByExample(example);

        List<Integer> roleIdList = new ArrayList<>(keyList.size());
        for (UserRoleKey userRoleKey : keyList) {
            roleIdList.add(userRoleKey.getRoleId());
        }
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdIn(roleIdList);
        return roleMapper.selectByExample(roleExample);
    }
    //根据用户的id查询所有权限信息
    @Override
    public List<Permission> findPermissions(String name) {
        List<Role> roles = findRoles(name);
        List<Integer> roleIds = new ArrayList<>(roles.size());
        for (Role role : roles) {
            roleIds.add(role.getId());
        }
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRoleIdIn(roleIds);
        List<RolePermissionKey> keyList = rolePermissionMapper.selectByExample(example);
        List<Integer> permissionIdList = new ArrayList<>(keyList.size());
        for (RolePermissionKey rolePermissionKey : keyList) {
            permissionIdList.add(rolePermissionKey.getPermissionId());
        }
        PermissionExample permissionExample = new PermissionExample();
        if (permissionIdList.size() != 0) {
            permissionExample.createCriteria().andIdIn(permissionIdList);
            return permissionMapper.selectByExample(permissionExample);
        }
        return new ArrayList<>();
    }

    @Override
    public User findUserByUsername(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria().andUsernameEqualTo(username);
        return userMapper.selectByExample(userExample).get(0);
    }
    @Transactional
    @Override
    public int assignDefaultUserRolePermission(User user) {
        int success1 = userMapper.insert(user);
        UserRole userRoleKey = new UserRole();
        userRoleKey.setUserId(user.getId());
        userRoleKey.setRoleId(2);
        int success2 = userRoleMapper.insert(userRoleKey);
        return success1 + success2;
    }

    @Override
    public int insertUser(User user) {
        int insert=0;
        insert = userMapper.insert(user);

        return insert;
    }

    @Override
    public List<User> selectAll() {
        List<User> users = userMapper.selectByExample(null);

        return users;
    }

    @Override
    public int updateUser(User user) {
        int i = userMapper.updateByPrimaryKey(user);
        return i;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateUserRole(UserRoleKey userRoleKey,UserRole userRole) {
        int update;
        try {
            UserRoleExample userRoleExample = new UserRoleExample();
            UserRoleExample.Criteria criteria = userRoleExample.createCriteria().andUserIdEqualTo(userRoleKey.getUserId()).andRoleIdEqualTo(userRoleKey.getRoleId());
            update = userRoleMapper.updateByExample(userRole,userRoleExample);
            if (update!=0){
                return update;
            }else{
                int i=update/0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public UserRole findByUidAndRid(UserRoleKey userRoleKey) {
        UserRole userRole = userRoleMapper.selectByPrimaryKey(userRoleKey);
        return userRole;
    }

    @Override
    public int deleteById(Integer id) {
        int i = userMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteByUidAndRid(UserRoleKey userRoleKey) {
        int delete;
        System.out.println("ss:"+userRoleKey);
        try {
            delete = userRoleMapper.deleteByPrimaryKey(userRoleKey);
            if (delete!=0){
                return delete;
            }else{
                int i=delete/0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertUserRole(UserRole userRole) {
        int insert;
        try {
            insert = userRoleMapper.insert(userRole);
            if (insert!=0){
                return insert;
            }else{
                int i=insert/0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }
}
