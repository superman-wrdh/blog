package com.hc.entity.base;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 博客实体
 * @author Administrator
 *
 */
public class BlogBase implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; // 编号
	private String title; // 博客标题
	private String summary; // 摘要
	private Date releaseDate; // 发布日期
	private Integer clickHit; // 查看次数
	private Integer replyHit; // 回复次数
	private String content; // 博客内容
	private String contentNoTag; // 博客内容 无网页标签 Lucene分词用
	private BlogTypeBase blogType; // 博客类型

	private Integer blogCount; // 博客数量 非博客实际属性，主要是 根据发布日期归档查询博客数量用
	private String releaseDateStr; // 发布日期字符串 只取年和月
	private String keyWord; // 关键字 空格隔开

	private List<String> imagesList=new LinkedList<String>(); // 博客里存在的图片 主要用于列表展示显示缩略图

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Integer getClickHit() {
		return clickHit;
	}
	public void setClickHit(Integer clickHit) {
		this.clickHit = clickHit;
	}
	public Integer getReplyHit() {
		return replyHit;
	}
	public void setReplyHit(Integer replyHit) {
		this.replyHit = replyHit;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentNoTag() {
		return contentNoTag;
	}
	public void setContentNoTag(String contentNoTag) {
		this.contentNoTag = contentNoTag;
	}
	public BlogTypeBase getBlogType() {
		return blogType;
	}
	public void setBlogType(BlogTypeBase blogType) {
		this.blogType = blogType;
	}
	public Integer getBlogCount() {
		return blogCount;
	}
	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}
	public String getReleaseDateStr() {
		return releaseDateStr;
	}
	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public List<String> getImagesList() {
		return imagesList;
	}
	public void setImagesList(List<String> imagesList) {
		this.imagesList = imagesList;
	}



}
