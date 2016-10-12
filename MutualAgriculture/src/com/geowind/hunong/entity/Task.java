package com.geowind.hunong.entity;

import java.util.Date;

/**
 * 任务实体类
 * Created by Jiang on 2016/7/20.
 */
public class Task {
    //任务编号
    private int no;
    //农机手
    private String mUname;
    //种粮大户
    private String fUname;
    //农田编号
    private int fno;
    //工作量
    private String workLoad;
    //农机编号
    private int mno;
    //作业类型
    private String type;
    //日期
    private String date;
    //状态
    private int state;
    //农田分区编号
    private int fzno;
    //农田总面积
    private double farea;
    //农田地址
    private String faddr;
    //经度
    private String longitude;
    //纬度
    private String latitude;
    //农田照片
    private String fpic;
    //作物类型
    private String cropType;
    //农机类型
    private String mstyle;
    //备注
    private String note;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getmUname() {
        return mUname;
    }

    public void setmUname(String mUname) {
        this.mUname = mUname;
    }

    public String getfUname() {
        return fUname;
    }

    public void setfUname(String fUname) {
        this.fUname = fUname;
    }

    public int getFno() {
        return fno;
    }

    public void setFno(int fno) {
        this.fno = fno;
    }

    public String getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(String workLoad) {
        this.workLoad = workLoad;
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getFzno() {
        return fzno;
    }

    public void setFzno(int fzno) {
        this.fzno = fzno;
    }

    public double getFarea() {
        return farea;
    }

    public void setFarea(double farea) {
        this.farea = farea;
    }

    public String getFaddr() {
        return faddr;
    }

    public void setFaddr(String faddr) {
        this.faddr = faddr;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getFpic() {
        return fpic;
    }

    public void setFpic(String fpic) {
        this.fpic = fpic;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public String getMstyle() {
        return mstyle;
    }

    public void setMstyle(String mstyle) {
        this.mstyle = mstyle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Task{" +
                "no=" + no +
                ", mUname='" + mUname + '\'' +
                ", fUname='" + fUname + '\'' +
                ", fno=" + fno +
                ", workLoad='" + workLoad + '\'' +
                ", mno=" + mno +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", state=" + state +
                ", fzno=" + fzno +
                ", farea=" + farea +
                ", faddr='" + faddr + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", fpic='" + fpic + '\'' +
                ", cropType='" + cropType + '\'' +
                ", mstyle='" + mstyle + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
