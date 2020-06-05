package com.ftt.forum.controller;

import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.PostMapper;
import com.ftt.forum.mapper.UserMapper;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostMapper postMapper;
    @Autowired
    UserMapper userMapper;

    @PostMapping("/addPost")
    public String addPost(String content, HttpSession session){
        Post post = new Post();
        User user = (User) session.getAttribute("user");
        post.setUid(user.getId());
        post.setContent(content);
        Date now = new Date();
        post.setCreate_date(now);
        post.setUpdate_date(now);
        postMapper.insert(post);
        return  "redirect:postList";
    }

    @GetMapping("/postList")
    public String postList(Model model) {
        List<Post> posts = postMapper.selectList();
        for (Post post : posts) {
            User user = userMapper.selectById(post.getUid());
            post.setUser(user);
        }
        model.addAttribute("posts", posts);
        return "postList";
    }
}
