package com.ftt.forum.mapper;

import com.ftt.forum.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Insert("insert into user (name, password) values (#{name}, #{password})")
    void insert(User user);
}
