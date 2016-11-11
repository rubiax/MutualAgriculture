package com.geowind.hunong.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Pestzone entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pestzone", catalog = "mutualagriculture")
public class Pestzone implements java.io.Serializable {

	// Fields

	private Integer pzid;
	private Zone zone;
	private String degree;
	private String ptype;
	private String itype;
	private String time;
	private Integer status;

	// Constructors

	/** default constructor */
	public Pestzone() {
	}

	/** full constructor */
	public Pestzone(Zone zone, String degree, String ptype, String itype,
			String time, Integer status) {
		this.zone = zone;
		this.degree = degree;
		this.ptype = ptype;
		this.itype = itype;
		this.time = time;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "pzid", unique = true, nullable = false)
	public Integer getPzid() {
		return this.pzid;
	}

	public void setPzid(Integer pzid) {
		this.pzid = pzid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zondId")
	public Zone getZone() {
		return this.zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	@Column(name = "degree", length = 45)
	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "ptype", length = 45)
	public String getPtype() {
		return this.ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	@Column(name = "itype", length = 45)
	public String getItype() {
		return this.itype;
	}

	public void setItype(String itype) {
		this.itype = itype;
	}

	@Column(name = "time", length = 45)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}