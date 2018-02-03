package com.hc.entity.base;

/**
 * Created by hc on 2017/4/27.
 */

import java.util.Date;

/**
 id int(11) NOT NULL
 title varchar(255) NULL
 artist varchar(255) NULL
 album varchar(255) NULL
 cover varchar(255) NULL
 mp3 varchar(255) NULL
 ogg varchar(255) NULL
 img varchar(255) NULL
 create_time datetime NULL
 update_time datetime NULL
 */
public class MusicBase {
    private int id;
    private String title;
    private String artist;
    private String album;
    private String cover;
    private String mp3;
    private String ogg;
    private String img;
    private Date create_time;
    private Date update_time;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getCover() {
        return cover;
    }

    public String getMp3() {
        return mp3;
    }

    public String getOgg() {
        return ogg;
    }

    public String getImg() {
        return img;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public void setOgg(String ogg) {
        this.ogg = ogg;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
