package com.geowind.hunong.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * Article entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "article", catalog = "mutualagriculture")
public class Article implements java.io.Serializable {

	// Fields
	@Expose
	private Integer articleId;
	@Expose
	private Integer category;
	@Expose
	private String title;
	@Expose
	private String url;
	@Expose
	private String description;
	@Expose
	private String author;

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** full constructor */
	public Article(Integer category, String title, String url,
			String description, String author) {
		this.category = category;
		this.title = title;
		this.url = url;
		this.description = description;
		this.author = author;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "articleId", unique = true, nullable = false)
	public Integer getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	@Column(name = "category")
	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	@Column(name = "title", length = 45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "url", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "author", length = 45)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}