package com.fyd.cygl.entity;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class User {
    private Integer id;

    private String username;

    private String password;

    private Date createTime;

    private String salt;

    private Integer roleId;
    private String timeformat;
    private String rolename;
    //修改时间为指定格式
    public String getTimeformat() {
        return timeformat;
    }

    public void setTimeformat() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(this.getCreateTime());
        this.timeformat =dateString ;
    }
    //修改为指定角色名称
    public String getRolename() {
        return rolename;
    }

    public void setRolename() {
        if (this.getRoleId()==1){
            this.rolename ="超级管理员";
        }else if (this.getRoleId()==2){
            this.rolename="餐饮老板";
        }else if (this.getRoleId()==3){
            this.rolename="员工";
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", salt='" + salt + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSalt() { return salt; }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }
    //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
}