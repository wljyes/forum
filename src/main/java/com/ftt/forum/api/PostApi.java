package com.ftt.forum.api;

import com.ftt.forum.dto.PostResponse;
import com.ftt.forum.dto.Response;
import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.CommentMapper;
import com.ftt.forum.mapper.PostMapper;
import com.ftt.forum.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PostApi {

    private final PostMapper postMapper;
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;

    public PostApi(PostMapper postMapper, UserMapper userMapper, CommentMapper commentMapper) {
        this.postMapper = postMapper;
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/api/post")
    public Response addPost(String content, HttpSession session) {
        Post post = new Post();
        int uid = (int) session.getAttribute("userId");
        post.setUid(uid);
        post.setContent(content);
        Date now = new Date();
        post.setCreate_date(now);

        postMapper.insert(post);
        return Response.success().append("msg", "发表成功！");
    }

    @GetMapping("/api/post")
    public Response getAllPost() {
        List<PostResponse> postResponses = new ArrayList<>();

        List<Post> posts = postMapper.selectList();
        //设置user
        for (Post post : posts) {
            User postUser = userMapper.selectById(post.getUid());
            fill(post, postUser);

            postResponses.add(PostResponse.of(post));
        }
        return Response.success().append("posts", postResponses);
    }

    @GetMapping("/api/post/{pid}")
    public Response getPost(@PathVariable("pid") int pid) {
        Post post = postMapper.selectById(pid);
        User poster = userMapper.selectById(post.getUid());
        fill(post, poster);

        return Response.success().append("post", PostResponse.of(post));
    }

    @GetMapping("/api/{uid}/post")
    public Response getUserPosts(@PathVariable("uid") int uid) {
        User user = userMapper.selectById(uid);
        List<PostResponse> responses = new ArrayList<>();
        List<Post> posts = postMapper.selectByUid(uid);
        for (Post post : posts) {
            fill(post, user);
            responses.add(PostResponse.of(post));
        }
        return Response.success().append("posts", responses);
    }

    public void fill(Post post, User poster) {
        post.setUser(poster);
        post.setCommentCount(commentMapper.selectCountByPid(post.getId()));
        Date updateDate = commentMapper.selectLastCommentDate(post.getId());
        if (updateDate == null)
            updateDate = post.getCreate_date();
        post.setUpdate_date(updateDate);
    }
}
