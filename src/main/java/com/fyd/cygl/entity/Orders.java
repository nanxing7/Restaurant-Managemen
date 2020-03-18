package com.fyd.cygl.entity;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class Orders {
    private String oid;

    private Integer tid;

    private Integer number;

    private Integer sid;

    private Date time;

    private Double preprice;

    private Double discountprice;

    private Double realprice;

    private String payment;

    private String phone;

    private String name;

    private Integer status;

    private String timestr;


    @Override
    public String toString() {
        return "Orders{" +
                "oid='" + oid + '\'' +
                ", tid=" + tid +
                ", number=" + number +
                ", sid=" + sid +
                ", time=" + time +
                ", preprice=" + preprice +
                ", discountprice=" + discountprice +
                ", realprice=" + realprice +
                ", payment='" + payment + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", timestr='" + timestr + '\'' +
                '}';
    }

    public String getTimestr() {
        return timestr;
    }

    public void setTimestr(String timestr) {this.timestr = timestr; }
    public void turnToTimestr(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setTimestr(sdf.format(this.getTime()));
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getPreprice() {
        return preprice;
    }

    public void setPreprice(Double preprice) {
        this.preprice = preprice;
    }

    public Double getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(Double discountprice) {
        this.discountprice = discountprice;
    }

    public Double getRealprice() {
        return realprice;
    }

    public void setRealprice(Double realprice) {
        this.realprice = realprice;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}