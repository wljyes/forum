package com.ftt.forum.api;

import com.ftt.forum.dto.PostResponse;
import com.ftt.forum.dto.Response;
import com.ftt.forum.entity.Collect;
import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.CollectMapper;
import com.ftt.forum.mapper.PostMapper;
import com.ftt.forum.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CollectApi {
    private final CollectMapper collectMapper;
    private final PostMapper postMapper;
    private final UserMapper userMapper;

    public CollectApi(CollectMapper collectMapper, PostMapper postMapper, UserMapper userMapper) {
        this.collectMapper = collectMapper;
        this.postMapper = postMapper;
        this.userMapper = userMapper;
    }

    @PostMapping("/api/collect")
    public Response addCollect(int pid, HttpSession session) {
        int uid = (int) session.getAttribute("userId");
        Collect collect = new Collect();
        collect.setUid(uid);
        collect.setPid(pid);
        collect.setCollect_date(new Date());
        collectMapper.insert(collect);
        return Response.success();
    }

    @DeleteMapping("/api/collect/{pid}")
    public Response uncollect(@PathVariable("pid") int pid, HttpSession session) {
        int uid = (int) session.getAttribute("userId");
        collectMapper.deleteByUidAndPid(uid, pid);
        return Response.success();
    }




}
