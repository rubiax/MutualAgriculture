package com.geowind.hunong.jpa;

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

import com.google.gson.annotations.Expose;

/**
 * Aiplanning entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "aiplanning", catalog = "mutualagriculture")
public class Aiplanning implements java.io.Serializable {

	// Fields
	@Expose
	private Integer aid;
	@Expose
	private Block block;
	@Expose
	private String event;
	@Expose
	private Date begin;
	@Expose
	private Date end;
	@Expose
	private String bname;
	@Expose
	private Double maxdays;
	@Expose
	private Double days;
	@Expose
	private Integer mnum;
	@Expose
	private Double totalwork;
	@Expose
	private Double efficiency;

	// Constructors

	/** default constructor */
	public Aiplanning() {
	}

	/** full constructor */
	public Aiplanning(Block block, String event, Date begin, Date end,
			String bname, Double maxdays, Double days, Integer mnum,
			Double totalwork, Double efficiency) {
		this.block = block;
		this.event = event;
		this.begin = begin;
		this.end = end;
		this.bname = bname;
		this.maxdays = maxdays;
		this.days = days;
		this.mnum = mnum;
		this.totalwork = totalwork;
		this.efficiency = efficiency;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "aid", unique = true, nullable = false)
	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bid")
	public Block getBlock() {
		return this.block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	@Column(name = "event", length = 100)
	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "begin", length = 10)
	public Date getBegin() {
		return this.begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end", length = 10)
	public Date getEnd() {
		return this.end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Column(name = "bname", length = 100)
	public String getBname() {
		return this.bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	@Column(name = "maxdays", precision = 22, scale = 0)
	public Double getMaxdays() {
		return this.maxdays;
	}

	public void setMaxdays(Double maxdays) {
		this.maxdays = maxdays;
	}

	@Column(name = "days", precision = 22, scale = 0)
	public Double getDays() {
		return this.days;
	}

	public void setDays(Double days) {
		this.days = days;
	}

	@Column(name = "mnum")
	public Integer getMnum() {
		return this.mnum;
	}

	public void setMnum(Integer mnum) {
		this.mnum = mnum;
	}

	@Column(name = "totalwork", precision = 22, scale = 0)
	public Double getTotalwork() {
		return this.totalwork;
	}

	public void setTotalwork(Double totalwork) {
		this.totalwork = totalwork;
	}

	@Column(name = "efficiency", precision = 22, scale = 0)
	public Double getEfficiency() {
		return this.efficiency;
	}

	public void setEfficiency(Double efficiency) {
		this.efficiency = efficiency;
	}

	@Override
	public String toString() {
		return "Aiplanning [aid=" + aid + ", block=" + block + ", event="
				+ event + ", begin=" + begin + ", end=" + end + ", bname="
				+ bname + ", maxdays=" + maxdays + ", days=" + days
				+ ", mnum=" + mnum + ", totalwork=" + totalwork
				+ ", efficiency=" + efficiency + "]";
	}
	
	

}