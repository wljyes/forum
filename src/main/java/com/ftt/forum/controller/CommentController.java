package com.ftt.forum.controller;

import com.ftt.forum.entity.Comment;
import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.CommentMapper;
import com.ftt.forum.mapper.PostMapper;
import com.ftt.forum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    PostMapper postMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;

    @PostMapping("/addComment")
    @Transactional
    public String  addComment(int pid, String content, HttpSession session){
        User user = (User) session.getAttribute("user");
        Comment comment = new Comment();
        //设置uid
        comment.setUid(user.getId());
        comment.setPid(pid);
        comment.setContent(content);
        Date now = new Date();
        comment.setCreate_date(now);

        Post post = new Post();
        post.setId(pid);
        post.setUpdate_date(now);
        commentMapper.insert(comment);
        return "redirect:commentList?pid=" + pid;
    }

    @GetMapping("/uncomment")
    public String uncomment(int id, int pid){
        commentMapper.delete(id);
        return "redirect:commentList?pid=" + pid;
    }

    @GetMapping("/commentList")
    public String commentList(int pid, Model model){
        Post post = postMapper.selectById(pid);
        post.setUser(userMapper.selectById(post.getUid()));

        List<Comment> comments = commentMapper.select(pid);
        for (Comment comment : comments)
        {
            User user = userMapper.selectById(comment.getUid());
            comment.setUser(user);
        }
        model.addAttribute("comments",comments);
        model.addAttribute("post",post);
        return "commentList";
    }


}
