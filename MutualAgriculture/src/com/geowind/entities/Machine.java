package com.geowind.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Machine entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "machine", catalog = "mutualagriculture")
public class Machine implements java.io.Serializable {

	// Fields

	private Integer machineId;
	private Machineowner machineowner;
	private String plate;
	private String type;
	private String brand;
	private String horsepower;
	private Date overdate;
	private String picture;
	private Integer state;
	private Integer workstate;
	private Set<Task> tasks = new HashSet<Task>(0);

	// Constructors

	/** default constructor */
	public Machine() {
	}

	/** full constructor */
	public Machine(Machineowner machineowner, String plate, String type,
			String brand, String horsepower, Date overdate, String picture,
			Integer state, Integer workstate, Set<Task> tasks) {
		this.machineowner = machineowner;
		this.plate = plate;
		this.type = type;
		this.brand = brand;
		this.horsepower = horsepower;
		this.overdate = overdate;
		this.picture = picture;
		this.state = state;
		this.workstate = workstate;
		this.tasks = tasks;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "machineId", unique = true, nullable = false)
	public Integer getMachineId() {
		return this.machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ownerId")
	public Machineowner getMachineowner() {
		return this.machineowner;
	}

	public void setMachineowner(Machineowner machineowner) {
		this.machineowner = machineowner;
	}

	@Column(name = "plate", length = 45)
	public String getPlate() {
		return this.plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	@Column(name = "type", length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "brand", length = 45)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "horsepower", length = 45)
	public String getHorsepower() {
		return this.horsepower;
	}

	public void setHorsepower(String horsepower) {
		this.horsepower = horsepower;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "overdate", length = 10)
	public Date getOverdate() {
		return this.overdate;
	}

	public void setOverdate(Date overdate) {
		this.overdate = overdate;
	}

	@Column(name = "picture", length = 200)
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "workstate")
	public Integer getWorkstate() {
		return this.workstate;
	}

	public void setWorkstate(Integer workstate) {
		this.workstate = workstate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "machine")
	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

}