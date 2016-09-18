package com.geowind.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Article entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "article", catalog = "mutualagriculture")
public class Article implements java.io.Serializable {

	// Fields

	private Integer articleId;
	private Integer category;
	private String title;
	private String url;
	private String headContent;

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** full constructor */
	public Article(Integer category, String title, String url,
			String headContent) {
		this.category = category;
		this.title = title;
		this.url = url;
		this.headContent = headContent;
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

	@Column(name = "headContent", length = 500)
	public String getHeadContent() {
		return this.headContent;
	}

	public void setHeadContent(String headContent) {
		this.headContent = headContent;
	}

}