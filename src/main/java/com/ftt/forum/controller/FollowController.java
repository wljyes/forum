package com.ftt.forum.controller;

import com.ftt.forum.entity.Follow;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.FollowMapper;
import com.ftt.forum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FollowController {
    @Autowired
    FollowMapper followMapper;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/followList")
    public String followList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Follow> follows = followMapper.selectByUid(user.getId());
        List<User> followUsers = new ArrayList<>();
        for (Follow follow : follows) {
            User followUser = userMapper.selectById(follow.getFollower_id());
        }
        model.addAttribute("followUsers", followUsers);
        return "followList";
    }
}
