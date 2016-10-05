package com.geowind.hunong.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Task entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "task", catalog = "mutualagriculture")
public class Task implements java.io.Serializable {

	// Fields

	private Integer taskId;
	private User user;
	private Machine machine;
	private Farmland farmland;
	private Integer workload;
	private Date date;
	private String type;
	private Boolean finished;

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
	public Task(User user, Machine machine, Farmland farmland,
			Integer workload, Date date, String type, Boolean finished) {
		this.user = user;
		this.machine = machine;
		this.farmland = farmland;
		this.workload = workload;
		this.date = date;
		this.type = type;
		this.finished = finished;
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
	@Column(name = "date", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "type", length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "finished")
	public Boolean getFinished() {
		return this.finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

}