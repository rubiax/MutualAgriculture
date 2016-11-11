package com.geowind.hunong.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Servicestation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "servicestation", catalog = "mutualagriculture")
public class Servicestation implements java.io.Serializable {

	// Fields

	private Integer sid;
	private Double longitude;
	private Double latitude;
	private String spname;
	private String sptel;

	// Constructors

	/** default constructor */
	public Servicestation() {
	}

	/** full constructor */
	public Servicestation(Double longitude, Double latitude, String spname,
			String sptel) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.spname = spname;
		this.sptel = sptel;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sid", unique = true, nullable = false)
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
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

	@Column(name = "spname", length = 45)
	public String getSpname() {
		return this.spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	@Column(name = "sptel", length = 45)
	public String getSptel() {
		return this.sptel;
	}

	public void setSptel(String sptel) {
		this.sptel = sptel;
	}

}