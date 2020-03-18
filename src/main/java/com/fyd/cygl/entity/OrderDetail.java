package com.fyd.cygl.entity;

import org.springframework.stereotype.Component;

@Component
public class OrderDetail {
    private Integer odid;

    private Integer number;

    private String oid;

    private String fname;

    private Double price;

    private String tips;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "odid=" + odid +
                ", number=" + number +
                ", oid='" + oid + '\'' +
                ", fname='" + fname + '\'' +
                ", price=" + price +
                ", tips='" + tips + '\'' +
                '}';
    }

    public Integer getOdid() {
        return odid;
    }

    public void setOdid(Integer odid) {
        this.odid = odid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }
}