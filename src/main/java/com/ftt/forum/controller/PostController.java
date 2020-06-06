package com.ftt.forum.controller;

import com.ftt.forum.entity.Comment;
import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.CommentMapper;
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
    @Autowired
    CommentMapper commentMapper;

    /**
     * 发送新贴子, 发送完书跳转到 /postList
     * @param content 帖子内容
     * @param session session存有user
     * @return 让客户端请求 /postList
     */
    @PostMapping("/addPost")
    public String addPost(String content, HttpSession session){
        //new 一个新 Post
        Post post = new Post();
        //从session中取出user
        User user = (User) session.getAttribute("user");
        //给post设置uid, content...等等
        post.setUid(user.getId());
        post.setContent(content);
        Date now = new Date();
        post.setCreate_date(now);
        post.setUpdate_date(now);
        //插入到数据库
        postMapper.insert(post);
        //让浏览器请求/postList，从而用户看到最新发出的帖子
        return  "redirect:postList";
    }

    /**
     * 帖子列表，按更新时间倒叙排列
     * @return 在服务端渲染用 model 渲染 postList.html, 并把渲染后的html返回个浏览器
     */
    @GetMapping("/postList")
    public String postList(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //从数据库查询出post
        List<Post> posts = postMapper.selectList();
        //给每一个post设置对应的user对象
        for (Post post : posts) {
            //由post的uid从数据库查询对应的User，并传给post
            User postUser = userMapper.selectById(post.getUid());
            post.setUser(postUser);
            post.setCommentCount(commentMapper.selectCountByPid(post.getId()));
        }
        //将取得的posts传给模板做渲染，每个post都包含user,可以在渲染时使用用户信息
        model.addAttribute("posts", posts);
        model.addAttribute("user", user);
        return "postList";
    }
}
