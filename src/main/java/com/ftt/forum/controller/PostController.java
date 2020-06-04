package com.ftt.forum.controller;

import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

public class PostController {

    @Autowired
    PostMapper postMapper;

    @PostMapping("/addPost")
    public String addPost(String content, HttpSession session){
        Post post = new Post();
        User user = (User) session.getAttribute("user");
        post.setUid(user.getId());
        post.setContent(content);
        post.setCreate_date(new Date());
        return  "addPost";
    }
}
