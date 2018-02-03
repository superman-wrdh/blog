package com.hc.entity;

import com.hc.entity.base.BlogTypeBase;

import java.util.Date;

/**
 * Created by hc on 2017/3/13.
 */
public class SearchVo {
    private Integer id;
    private String title;
    private Integer clickHit;
    private Integer blogType;
    private String releaseDateStr;
    private String keyWord;
    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClickHit(Integer clickHit) {
        this.clickHit = clickHit;
    }

    public void setBlogType(Integer blogType) {
        this.blogType = blogType;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getClickHit() {
        return clickHit;
    }

    public Integer getBlogType() {
        return blogType;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public String getKeyWord() {
        return keyWord;
    }
}
