package com.ftt.forum.api;

import com.ftt.forum.dto.Response;
import com.ftt.forum.entity.Collect;
import com.ftt.forum.mapper.CollectMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class CollectApi {
    private final CollectMapper collectMapper;

    public CollectApi(CollectMapper collectMapper) {
        this.collectMapper = collectMapper;
    }

    @PostMapping("/api/collect")
    public Response<String> addCollect(int pid, HttpSession session) {
        int uid = (int) session.getAttribute("userId");
        Collect collect = new Collect();
        collect.setUid(uid);
        collect.setPid(pid);
        collect.setCollect_date(new Date());
        collectMapper.insert(collect);
        return Response.success("");
    }

    @DeleteMapping("/api/collect/{pid}")
    public Response<String> uncollect(@PathVariable("pid") int pid, HttpSession session) {
        int uid = (int) session.getAttribute("userId");
        collectMapper.deleteByUidAndPid(uid, pid);
        return Response.success("");
    }


}
