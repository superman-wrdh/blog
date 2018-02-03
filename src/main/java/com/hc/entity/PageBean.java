package com.hc.entity;

import java.util.List;

/**
 * 分页Model类
 * @author
 *
 */
public class PageBean {

	private int page; // 第几页
	private int pageSize; // 每页记录数
	private int start;  // 起始页
	private int resultSize;
    private List<Blog> results;



	public PageBean(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.start=(page-1)*pageSize;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return (page-1)*pageSize;
	}

	public List<Blog> getResults() {
		return results;
	}

	public void setResults(List<Blog> results) {
		this.results = results;
	}

	public void setResultSize(int resultSize) {
		this.resultSize = resultSize;
	}

	public int getResultSize() {
		return resultSize;
	}
}
