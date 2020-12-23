package com.ftt.forum.api;

import com.ftt.forum.dto.CommentResponse;
import com.ftt.forum.dto.Response;
import com.ftt.forum.entity.Comment;
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
public class CommentApi {
    private final CommentMapper commentMapper;
    private final UserMapper userMapper;

    public CommentApi(PostMapper postMapper, CommentMapper commentMapper, UserMapper userMapper) {
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
    }

    @PostMapping("/api/{pid}/comment")
    public Response addComment(@PathVariable("pid") int pid, String content, HttpSession session) {
        int uid = (int) session.getAttribute("userId");
        Comment comment = new Comment();
        comment.setUid(uid);
        comment.setPid(pid);
        comment.setContent(content);
        Date now = new Date();
        comment.setCreate_date(now);

        commentMapper.insert(comment);
        return Response.success("");
    }

    @GetMapping("/api/{pid}/comment")
    public Response getComments(@PathVariable("pid") int pid) {
        List<CommentResponse> responses = new ArrayList<>();
        for (Comment comment : commentMapper.select(pid)) {
            User user = userMapper.selectById(comment.getUid());
            responses.add(CommentResponse.of(comment));
        }
        return Response.success(responses);
    }
}
