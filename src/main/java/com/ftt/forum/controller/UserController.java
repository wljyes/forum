package com.ftt.forum.controller;

import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public String login(String name, String password, HttpSession session, Model model) {
        User user = userMapper.selectByName(name);
        if (user == null || !user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return "index";
        }
        session.setAttribute("user", null);
        model.addAttribute("error", "用户名或密码错误");
        return "login";
    }

    @PostMapping("/register")
    public String register(String name, String password, Model model) {
        User user = userMapper.selectByName(name);
        if (user == null) {
            user = new User();
            user.setName(name);
            user.setPassword(password);
            userMapper.insert(user);
            return "login";
        }
        model.addAttribute("error", "用户名已存在");
        return "register";
    }
}
