package com.ftt.forum.controller;

import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.PostMapper;
import com.ftt.forum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PostMapper postMapper;


    @PostMapping("/user/login")
    public String login(String name, String password, HttpSession session, Model model) {
        User user = userMapper.selectByName(name);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return "redirect:/index";
        }
        session.setAttribute("user", null);
        model.addAttribute("error", "用户名或密码错误");
        return "login";
    }

    @PostMapping("/user/register")
    public String register(String name, String password, Model model) {
        User user = userMapper.selectByName(name);
        if (user == null) {
            user = new User();
            user.setName(name);
            user.setPassword(password);
            userMapper.insert(user);
            return "redirect:/login";
        }
        model.addAttribute("error", "用户名已存在");
        return "register";
    }

    @GetMapping("/userinfo")
    public String userinfo(int uid, Model model) {
        User user = userMapper.selectById(uid);
        user.setPassword(null);
        List<Post> posts = postMapper.selectByUid(uid);
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        return "userinfo";
    }
}
