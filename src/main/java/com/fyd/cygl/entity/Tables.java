package com.fyd.cygl.entity;

import org.springframework.stereotype.Component;

@Component
public class Tables {
    private Integer tid;

    private Integer seatnum;

    private String room;

    private Integer status;

    @Override
    public String toString() {
        return "Tables{" +
                "tid=" + tid +
                ", seatnum=" + seatnum +
                ", room='" + room + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getSeatnum() {
        return seatnum;
    }

    public void setSeatnum(Integer seatnum) {
        this.seatnum = seatnum;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}