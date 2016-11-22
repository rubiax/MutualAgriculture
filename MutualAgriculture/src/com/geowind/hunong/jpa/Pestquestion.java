package com.geowind.hunong.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * Pestquestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pestquestion", catalog = "mutualagriculture")
public class Pestquestion implements java.io.Serializable {

	// Fields
	@Expose
	private Integer qid;
	@Expose
	private User user;
	@Expose
	private Pestlib pestlib;
	@Expose
	private String uploadPic;
	@Expose
	private String descr;

	// Constructors

	/** default constructor */
	public Pestquestion() {
	}

	/** full constructor */
	public Pestquestion(User user, Pestlib pestlib, String uploadPic,
			String descr) {
		this.user = user;
		this.pestlib = pestlib;
		this.uploadPic = uploadPic;
		this.descr = descr;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "qid", unique = true, nullable = false)
	public Integer getQid() {
		return this.qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
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
	@JoinColumn(name = "result")
	public Pestlib getPestlib() {
		return this.pestlib;
	}

	public void setPestlib(Pestlib pestlib) {
		this.pestlib = pestlib;
	}

	@Column(name = "uploadPic", length = 200)
	public String getUploadPic() {
		return this.uploadPic;
	}

	public void setUploadPic(String uploadPic) {
		this.uploadPic = uploadPic;
	}

	@Column(name = "descr", length = 200)
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

}