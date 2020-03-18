package com.fyd.cygl.entity;

import org.springframework.stereotype.Component;

@Component
public class UserRoleKey {
    private Integer roleId;

    private Integer userId;

    @Override
    public String toString() {
        return "UserRoleKey{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}