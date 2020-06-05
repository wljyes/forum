package com.ftt.forum.controller;

import com.ftt.forum.entity.Follow;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
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

    @GetMapping("/followerList")
    public String followerList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Follow> follows = followMapper.selectByFollowerId(user.getId());
        List<User> followers = new ArrayList<>();
        for (Follow follow : follows) {
            followers.add(userMapper.selectById(follow.getUid()));
        }
        model.addAttribute("followers", followers);
        return "followerList";
    }

    @GetMapping("/unfollow")
    public String unfollow(int id) {
        followMapper.delete(id);
        return "redirect:followList";
    }

    @GetMapping("/follow")
    @ResponseBody
    public String follow(int follower_id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Follow follow = new Follow();
        follow.setUid(user.getId());
        follow.setFollower_id(follower_id);
        follow.setFollow_date(new Date());
        followMapper.insert(follow);
        return "success";
    }
}
