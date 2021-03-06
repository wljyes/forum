package com.ftt.forum.api;

import com.ftt.forum.dto.Response;
import com.ftt.forum.dto.UserResponse;
import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserApi {

    private final UserMapper userMapper;

    public UserApi(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping("/api/signin")
    public Response<UserResponse> signin(String username, String password, HttpSession session) {
        User user = userMapper.selectByName(username);
        if (user == null)
            return Response.fail(null, "用户不存在！");
        if (!user.getPassword().equals(password))
            return Response.fail(null, "用户名或密码错误！");
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        return Response.success(UserResponse.of(user));
    }

    @PostMapping("/api/signup")
    public Response<String> signup(String username, String password) {
        if ((username == null || password == null) ||
            (username.trim().equals("") || password.trim().equals("")))
            return Response.fail("", "用户名或密码不合法！");
        if (username.length() < 5 || username.length() > 16)
            return Response.fail("","用户名长度需在6-16位之间！");
        if (password.length() < 5 || password.length() > 16)
            return Response.fail("","密码需在6-16位之间！");

        User userByName = userMapper.selectByName(username);
        if (userByName != null)
            return Response.fail("","用户名已存在！");

        userMapper.insert(new User(username, password));
        return Response.success("注册成功");
    }

    @GetMapping("/api/userinfo/{uid}")
    public Response<UserResponse> userinfo(@PathVariable("uid") int uid) {
        User user = userMapper.selectById(uid);
        return Response.success(UserResponse.of(user));
    }

    @GetMapping("/api/userinfo")
    public Response<UserResponse> userinfo(HttpSession session) {
        User user = userMapper.selectById((Integer) session.getAttribute("userId"));
        return Response.success(UserResponse.of(user));
    }

    @PutMapping("/api/user/username")
    public Response<UserResponse> changeName(String newUsername, HttpSession session) {
        int uid = (int) session.getAttribute("userId");
        User user = userMapper.selectById(uid);
        user.setUsername(newUsername);
        userMapper.update(user);
        return Response.success(UserResponse.of(user));
    }

    @PutMapping("/api/user/password")
    public Response<UserResponse> changePassword(String newPassword, HttpSession session) {
        int uid = (int) session.getAttribute("userId");
        User user = userMapper.selectById(uid);
        user.setPassword(newPassword);
        userMapper.update(user);
        return Response.success(UserResponse.of(user));
    }

    @GetMapping("/api/user/logout")
    public Response<String> logout(HttpSession session) {
        session.invalidate();
        return Response.success("success");
    }
}
