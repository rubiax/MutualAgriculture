package com.geowind.hunong.entity;

import com.geowind.hunong.jpa.Task;

public class SimTask {
	
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
    private String mno;
    //作业类型
    private String  type;
    //日期
    private String date;
    //状态
    private String state;
    //农田分区编号
    private String fzno;
    //农田总面积
    private double farea;
    //农田地址
    private String faddr;
    //经度
    private double longitude;
    //纬度
    private double latitude;
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
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFzno() {
		return fzno;
	}
	public void setFzno(String fzno) {
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
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
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
    
	
	public SimTask fromTask(Task task) {
		if(task == null) {
			return null;
		} else {
			SimTask simTask = new SimTask();
			simTask.setNo(task.getTaskId());
			simTask.setmUname(task.getUser().getUsername());
			simTask.setfUname(task.getFarmland().getUser().getUsername());
			simTask.setFno(task.getFarmland().getFarmlandId());
			simTask.setWorkLoad(String.valueOf(task.getWorkload()));
			simTask.setMno(task.getMachine().getPlate());
			simTask.setType(task.getType());
			simTask.setDate(task.getWorkdate());
			simTask.setState(String.valueOf(task.getFinished()));
			simTask.setFzno(task.getFarmland().getZone().getZonename());
			simTask.setFarea(task.getFarmland().getArea());
			simTask.setFaddr(task.getFarmland().getAddress());
			simTask.setLongitude(task.getFarmland().getLongitude());
			simTask.setLatitude(task.getFarmland().getLatitude());
			simTask.setFpic(task.getFarmland().getPicture());
			simTask.setCropType(task.getFarmland().getZone().getType());
			simTask.setMstyle(task.getMachine().getType());
			simTask.setNote(task.getDesrc());
			return simTask;
		}
	}
}