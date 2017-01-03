package com.geowind.hunong.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * Task entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "task", catalog = "mutualagriculture")
public class Task implements java.io.Serializable {

	// Fields
	@Expose
	private Integer taskId;
	@Expose
	private Center center;
	@Expose
	private User user;
	@Expose
	private Machine machine;
	@Expose
	private Farmland farmland;
	@Expose
	private Integer workload;
	@Expose
	private Date publishdate;
	@Expose
	private Date workdate;
	@Expose
	private String type;
	@Expose
	private Integer finished;
	@Expose
	private String desrc;

	// Constructors

	/** default constructor */
	public Task() {
	}

	/** minimal constructor */
	public Task(User user, Machine machine, Farmland farmland) {
		this.user = user;
		this.machine = machine;
		this.farmland = farmland;
	}

	/** full constructor */
	public Task(Center center, User user, Machine machine, Farmland farmland,
			Integer workload, Date publishdate, Date workdate, String type,
			Integer finished, String desrc) {
		this.center = center;
		this.user = user;
		this.machine = machine;
		this.farmland = farmland;
		this.workload = workload;
		this.publishdate = publishdate;
		this.workdate = workdate;
		this.type = type;
		this.finished = finished;
		this.desrc = desrc;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "taskId", unique = true, nullable = false)
	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "centerId")
	public Center getCenter() {
		return this.center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "machineId", nullable = false)
	public Machine getMachine() {
		return this.machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "farmlandId", nullable = false)
	public Farmland getFarmland() {
		return this.farmland;
	}

	public void setFarmland(Farmland farmland) {
		this.farmland = farmland;
	}

	@Column(name = "workload")
	public Integer getWorkload() {
		return this.workload;
	}

	public void setWorkload(Integer workload) {
		this.workload = workload;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "publishdate", length = 10)
	public Date getPublishdate() {
		return this.publishdate;
	}

	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "workdate", length = 10)
	public Date getWorkdate() {
		return this.workdate;
	}

	public void setWorkdate(Date workdate) {
		this.workdate = workdate;
	}

	@Column(name = "type", length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "finished")
	public Integer getFinished() {
		return this.finished;
	}

	public void setFinished(Integer finished) {
		this.finished = finished;
	}

	@Column(name = "desrc", length = 1000)
	public String getDesrc() {
		return this.desrc;
	}

	public void setDesrc(String desrc) {
		this.desrc = desrc;
	}

}