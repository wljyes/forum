package com.ftt.forum.dto;

import com.ftt.forum.entity.Comment;

import java.util.Date;

public class CommentResponse {
    public final int id;
    public final int pid;
    public final UserResponse user;
    public final String content;
    public final Date date;

    public CommentResponse(int id, int pid, UserResponse user, String content, Date date) {
        this.id = id;
        this.pid = pid;
        this.user = user;
        this.content = content;
        this.date = date;
    }


    public static CommentResponse of(Comment comment) {
        return new CommentResponse(comment.getId(), comment.getPid(),
                UserResponse.of(comment.getUser()), comment.getContent(), comment.getCreate_date());
    }
}
