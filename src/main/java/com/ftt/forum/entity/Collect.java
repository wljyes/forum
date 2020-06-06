package com.ftt.forum.entity;

import java.util.Date;

public class Collect {//收藏

    private Integer id;
    private Integer pid;
    private Integer uid;
    private Date collect_date;
    private User user;
    private Post post;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCollect_date() {
        return collect_date;
    }

    public void setCollect_date(Date collect_date) {
        this.collect_date = collect_date;
    }

}
