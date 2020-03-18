package com.fyd.cygl.entity;

import org.springframework.stereotype.Component;

@Component
public class UserRole extends UserRoleKey {
    private String remarks;

    @Override
    public String toString() {
        return "UserRole{" +
                "remarks='" + remarks + '\'' +
                '}';
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}