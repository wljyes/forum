package com.ftt.forum.api;

import com.ftt.forum.dto.Response;
import com.ftt.forum.dto.UserResponse;
import com.ftt.forum.entity.Follow;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.FollowMapper;
import com.ftt.forum.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class FollowApi {
    private final FollowMapper followMapper;
    private final UserMapper userMapper;

    public FollowApi(FollowMapper followMapper, UserMapper userMapper) {
        this.followMapper = followMapper;
        this.userMapper = userMapper;
    }

    @GetMapping("/api/follow")
    public Response getFollows(HttpSession session) {
        int uid = (int) session.getAttribute("userId");
        List<Follow> follows = followMapper.selectByUid(uid);
        List<UserResponse> followers = new ArrayList<>();
        for (Follow follow : follows) {
            User follower = userMapper.selectById(follow.getFollower_id());
            followers.add(UserResponse.of(follower));
        }
        return Response.success(followers);
    }

    @PostMapping("/api/follow")
    public Response follow(int followId, HttpSession session) {
        int uid = (int) session.getAttribute("userId");
        Follow follow = followMapper.selectByUidAndFollowId(uid, followId);
        if (follow != null) {
            return Response.fail("已经关注过！");
        }
        Follow newFollow = new Follow();
        newFollow.setUid(uid);
        newFollow.setFollower_id(followId);
        newFollow.setFollow_date(new Date());
        followMapper.insert(newFollow);
        return Response.success("关注成功！");
    }

    @DeleteMapping("/api/follow/{followId}")
    public Response unfollow(@PathVariable("followId") int followId, HttpSession session) {
        int uid = (int) session.getAttribute("userId");
        followMapper.deleteByUidAndFollowId(uid, followId);
        return Response.success("取关成功！");
    }
}
