package com.geowind.hunong.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Center entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "center", catalog = "mutualagriculture")
public class Center implements java.io.Serializable {

	// Fields

	private Integer centerId;
	private String address;
	private String level;
	private String name;
	private String principal;
	private Integer valid;
	private Set<Zone> zones = new HashSet<Zone>(0);
	private Set<Machineowner> machineowners = new HashSet<Machineowner>(0);
	private Set<User> users = new HashSet<User>(0);

	// Constructors

	/** default constructor */
	public Center() {
	}

	/** full constructor */
	public Center(String address, String level, String name, String principal,
			Integer valid, Set<Zone> zones, Set<Machineowner> machineowners,
			Set<User> users) {
		this.address = address;
		this.level = level;
		this.name = name;
		this.principal = principal;
		this.valid = valid;
		this.zones = zones;
		this.machineowners = machineowners;
		this.users = users;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "centerId", unique = true, nullable = false)
	public Integer getCenterId() {
		return this.centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	@Column(name = "address", length = 80)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "level", length = 45)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "principal", length = 45)
	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Column(name = "valid")
	public Integer getValid() {
		return this.valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "center")
	public Set<Zone> getZones() {
		return this.zones;
	}

	public void setZones(Set<Zone> zones) {
		this.zones = zones;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "center")
	public Set<Machineowner> getMachineowners() {
		return this.machineowners;
	}

	public void setMachineowners(Set<Machineowner> machineowners) {
		this.machineowners = machineowners;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "center")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}