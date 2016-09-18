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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Farmland entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "farmland", catalog = "mutualagriculture")
public class Farmland implements java.io.Serializable {

	// Fields

	private Integer farmlandId;
	private User user;
	private Zone zone;
	private Double longitude;
	private Double latitude;
	private String address;
	private Double area;
	private String picture;
	private String transtion;
	private Double production;
	private String ph;
	private String npk;
	private Integer state;
	private Set<Task> tasks = new HashSet<Task>(0);

	// Constructors

	/** default constructor */
	public Farmland() {
	}

	/** full constructor */
	public Farmland(User user, Zone zone, Double longitude, Double latitude,
			String address, Double area, String picture, String transtion,
			Double production, String ph, String npk, Integer state,
			Set<Task> tasks) {
		this.user = user;
		this.zone = zone;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.area = area;
		this.picture = picture;
		this.transtion = transtion;
		this.production = production;
		this.ph = ph;
		this.npk = npk;
		this.state = state;
		this.tasks = tasks;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "farmlandId", unique = true, nullable = false)
	public Integer getFarmlandId() {
		return this.farmlandId;
	}

	public void setFarmlandId(Integer farmlandId) {
		this.farmlandId = farmlandId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zoneId")
	public Zone getZone() {
		return this.zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	@Column(name = "longitude", precision = 22, scale = 0)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", precision = 22, scale = 0)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "address", length = 80)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "area", precision = 22, scale = 0)
	public Double getArea() {
		return this.area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	@Column(name = "picture", length = 200)
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Column(name = "transtion", length = 45)
	public String getTranstion() {
		return this.transtion;
	}

	public void setTranstion(String transtion) {
		this.transtion = transtion;
	}

	@Column(name = "production", precision = 22, scale = 0)
	public Double getProduction() {
		return this.production;
	}

	public void setProduction(Double production) {
		this.production = production;
	}

	@Column(name = "pH", length = 45)
	public String getPh() {
		return this.ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	@Column(name = "NPK", length = 45)
	public String getNpk() {
		return this.npk;
	}

	public void setNpk(String npk) {
		this.npk = npk;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "farmland")
	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

}