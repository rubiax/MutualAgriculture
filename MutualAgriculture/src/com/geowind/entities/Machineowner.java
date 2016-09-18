package com.geowind.entities;

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
 * Machineowner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "machineowner", catalog = "mutualagriculture")
public class Machineowner implements java.io.Serializable {

	// Fields

	private Integer ownerId;
	private String phone;
	private String name;
	private String address;
	private Set<Machine> machines = new HashSet<Machine>(0);

	// Constructors

	/** default constructor */
	public Machineowner() {
	}

	/** full constructor */
	public Machineowner(String phone, String name, String address,
			Set<Machine> machines) {
		this.phone = phone;
		this.name = name;
		this.address = address;
		this.machines = machines;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ownerId", unique = true, nullable = false)
	public Integer getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	@Column(name = "phone", length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", length = 80)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "machineowner")
	public Set<Machine> getMachines() {
		return this.machines;
	}

	public void setMachines(Set<Machine> machines) {
		this.machines = machines;
	}

}