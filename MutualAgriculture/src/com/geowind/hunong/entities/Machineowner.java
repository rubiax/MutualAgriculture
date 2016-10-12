package com.geowind.hunong.entities;

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
 * Machineowner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "machineowner", catalog = "mutualagriculture")
public class Machineowner implements java.io.Serializable {

	// Fields

	private Integer ownerId;
	private Center center;
	private String name;
	private String sex;
	private Date birthday;
	private String phone;
	private String address;
	private Integer valid;
	private Set<Machine> machines = new HashSet<Machine>(0);

	// Constructors

	/** default constructor */
	public Machineowner() {
	}

	/** minimal constructor */
	public Machineowner(Center center, String name, String sex, Date birthday,
			String phone, String address) {
		this.center = center;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
	}

	/** full constructor */
	public Machineowner(Center center, String name, String sex, Date birthday,
			String phone, String address, Integer valid, Set<Machine> machines) {
		this.center = center;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.valid = valid;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "centerId", nullable = false)
	public Center getCenter() {
		return this.center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex", nullable = false, length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", nullable = false, length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "phone", nullable = false, length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address", nullable = false, length = 80)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "valid")
	public Integer getValid() {
		return this.valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "machineowner")
	public Set<Machine> getMachines() {
		return this.machines;
	}

	public void setMachines(Set<Machine> machines) {
		this.machines = machines;
	}

	@Override
	public String toString() {
		return "Machineowner [ownerId=" + ownerId + ", center=" + center
				+ ", name=" + name + ", sex=" + sex + ", birthday=" + birthday
				+ ", phone=" + phone + ", address=" + address + ", valid="
				+ valid + ", machines=" + machines + "]";
	}
	
	

}