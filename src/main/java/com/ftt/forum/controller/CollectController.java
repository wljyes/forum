package com.ftt.forum.controller;

import com.ftt.forum.entity.Collect;
import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.CollectMapper;
import com.ftt.forum.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CollectController {

    @Autowired
    CollectMapper collectMapper;
    @Autowired
    PostMapper postMapper;

    @PostMapping("/addCollect")
    @ResponseBody
    public String addCollect(int pid, HttpSession session){
        User user = (User) session.getAttribute("user");
        Collect collect = new Collect();
        collect.setUid(user.getId());
        collect.setPid(pid);
        collect.setCreate_date(new Date());
        collectMapper.insert(collect);
        return  "success";
    }

    @GetMapping("/uncollect")
    public String uncollect(int id){
        collectMapper.delete(id);
        return "redirect:collectList";
    }

    @GetMapping("/collectList")
    public String collectList(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Collect> collects = collectMapper.selectByUid(user.getId());
        for (Collect collect : collects) {
            Post post = postMapper.selectById(collect.getPid());
            collect.setPost(post);
        }
        model.addAttribute("collects",collects);
        return "collectList";
    }

}
