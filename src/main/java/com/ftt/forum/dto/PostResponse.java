package com.ftt.forum.dto;

import com.ftt.forum.entity.Post;

import java.util.Date;

public class PostResponse {
   public final int id;
   public final int uid;
   public final String username;
   public final Date createDate;
   public final Date updateDate;
   public final String content;
   public final int commentCount;

    public PostResponse(int id, int uid, String username, Date createDate, Date updateDate, String content, int commentCount) {
        this.id = id;
        this.uid = uid;
        this.username = username;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.content = content;
        this.commentCount = commentCount;
    }

    public static PostResponse of(Post post) {
        return new PostResponse(post.getId(), post.getUid(), post.getUser().getUsername(), post.getCreate_date(),
                post.getUpdate_date(), post.getContent(), post.getCommentCount());
    }

}
