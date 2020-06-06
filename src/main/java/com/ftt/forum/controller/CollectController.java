package com.ftt.forum.controller;

import com.ftt.forum.entity.Collect;
import com.ftt.forum.entity.User;
import com.ftt.forum.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public class CollectController {

    @Autowired
    CollectMapper collectMapper;

    @PostMapping("/addCollect")
    public String addCollect(int pid, HttpSession session){
        User user = (User) session.getAttribute("user");
        Collect collect = new Collect();
        collect.setUid(user.getId());
        collect.setPid(pid);
        collect.setCreate_date(new Date());
        collectMapper.insert(collect);
        return  "addCollect";
    }

    @GetMapping("/unselect")
    public String unselect(int id){
        collectMapper.delete(id);
        return "unselect";
    }

    @GetMapping("/collectList")
    public String collectList(int id, Model model){
        List<Collect> collects = collectMapper.selectById(id);
        model.addAttribute("collects",collects);
        return "collectList";
    }

}
